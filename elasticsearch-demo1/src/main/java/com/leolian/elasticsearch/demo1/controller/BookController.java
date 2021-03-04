/**
 * 
 */
package com.leolian.elasticsearch.demo1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * @author lianliang
 * @date 2017年12月3日 上午10:19:54
 */
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private TransportClient client;
	
	@GetMapping("/novel/{id}")
	@ResponseBody
	public ResponseEntity<Object> get (@PathVariable("id") String id){
		ResponseEntity<Object> response = null;
		if(null==id || id.trim().length()==0) {
			response = new ResponseEntity<Object>("请求参数id为空", HttpStatus.BAD_REQUEST);
			return response;
		}
		GetResponse result = client.prepareGet("book", "novel", id).get();
		if(!result.isExists()) {
			response = new ResponseEntity<Object>("id="+id+"数据不存在", HttpStatus.BAD_REQUEST);
			return response;
		}
		response = new ResponseEntity<Object>(result.getSource(), HttpStatus.FOUND);
		return response;
	}
	
	@PostMapping("/novel")
	@ResponseBody
	public ResponseEntity<Object> add(@RequestParam("title") String title,
								@RequestParam("author") String author,
								@RequestParam("wordCount") Integer word_count,
								@RequestParam("publishDate") 
								@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date publishDate) {
		ResponseEntity<Object> response = null;
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder()
			.startObject()
			.field("title", title)
			.field("author", author)
			.field("word_count", word_count)
			.field("publish_date", publishDate.getTime())
			.endObject();
			
			IndexResponse result = this.client.prepareIndex("book", "novel").setSource(builder).get();
			response = new ResponseEntity<Object>(result.getResult(), HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@DeleteMapping("/novel/{id}")
	@ResponseBody
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		DeleteResponse result = this.client.prepareDelete("book", "novel", id).get();
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
	@PutMapping("/novel/{id}")
	@ResponseBody
	public ResponseEntity<Object> update(@PathVariable("id") String id,
									@RequestParam(value="title", required=false) String title,
									@RequestParam(value="author", required=false) String author,
									@RequestParam(value="wordCount", required=false) Integer word_count,
									@RequestParam(value="publishDate", required=false) 
									@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date publishDate) {
		ResponseEntity<Object> response = null;
		UpdateRequest request = new UpdateRequest("book", "novel", id);
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
			if(null!=title) {
				builder.field("title", title);
			}
			if(null!=author) {
				builder.field("author", author);
			}
			if(null!=word_count) {
				builder.field("word_count", word_count);
			}
			if(null!=publishDate) {
				builder.field("publish_date", publishDate);
			}
			builder.endObject();
			request.doc(builder);
			UpdateResponse result = this.client.update(request).get();
			response = new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	
	@PostMapping("/novel/query")
	@ResponseBody
	public ResponseEntity<Object> query(@RequestParam(name="title", required=false) String title,
									@RequestParam(name="author", required=false) String author,
									@RequestParam(name="gtWordCount", required=false, defaultValue="0") Integer gtWordCount,
									@RequestParam(name="ltWordCount", required=false)  Integer ltWordCount) {
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		if(null!=author) {
			boolQuery.must(QueryBuilders.matchQuery("author", author));
		}
		if(null!=title) {
			boolQuery.must(QueryBuilders.matchQuery("title", title));
		}
		RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count").from(gtWordCount);
		if(null!=ltWordCount) {
			rangeQuery.to(ltWordCount);
		}
		boolQuery.filter(rangeQuery);
		
		SearchRequestBuilder builder = this.client.prepareSearch("book").setTypes("novel")
		.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		.setQuery(boolQuery)
		.setFrom(0)
		.setSize(100);
		System.out.println(builder);
		SearchResponse searchResponse = builder.get();
		List<Map<String, Object>> result = new ArrayList<>();
		for (SearchHit hit : searchResponse.getHits()) {
			result.add(hit.getSource());
		}
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
}
