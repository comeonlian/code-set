package com.leolian.code.fragment.book.distributed.chapter02.lucene;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Lucene 6.0 索引写入磁盘
 * @Description: 
 * @author lianliang
 * @date 2017年10月17日 下午5:23:43
 */
public class LuceneDemo03 {
	public static final String PATH = "F:\\Java\\Lucene\\index\\";
	
	
	public static void main(String[] args) throws Exception {
		
		// createIndex();

		// 创建query对象
		//Analyzer analyzer = new StandardAnalyzer();
		// 使用QueryParser搜索时，需要指定分词器，搜索时的分词器要和索引时的分词器一致
		// 第一个参数：默认搜索的域的名称
		//QueryParser parser = new QueryParser("description", analyzer);
		// 通过queryparser来创建query对象
		// 参数：输入的lucene的查询语句(关键字一定要大写)
		//Query query = parser.parse("description:java AND lucene");
		
		//Term term = new Term("name", "solr");
		
		//deleteIndex(term);
		
		/*Document doc = new Document();
		doc.add(new TextField("id", Integer.toString(22), Field.Store.YES));
		doc.add(new TextField("name", "solr", Field.Store.YES));
		doc.add(new TextField("price", Float.toString(86.14f), Field.Store.YES));
		doc.add(new TextField("pic", "967411.jpg", Field.Store.YES));
		doc.add(new TextField("description", "solr是一个全文检索服务 # 呵呵呵呵呵呵呵...", Field.Store.YES));
		
		updateIndex(term, doc);*/
		
		//Term term = new Term("name", "s*");
		//WildcardQuery query = new WildcardQuery(term);
		
		//doSearch(query);
		
	}
	
	/**
	 * 更新索引
	 * @param term
	 * @param doc
	 */
	public static void updateIndex(Term term, Document doc) {
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
		Directory directory;
		try {
			directory = FSDirectory.open(FileSystems.getDefault().getPath(PATH));
			IndexWriter indexWriter = new IndexWriter(directory, cfg);
			indexWriter.updateDocument(term, doc);
			indexWriter.commit();
			indexWriter.close();
			System.out.println("更新完成!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除索引
	 */
	public static void deleteIndex(Term term) {
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
		Directory directory;
		try {
			directory = FSDirectory.open(FileSystems.getDefault().getPath(PATH));
			IndexWriter indexWriter = new IndexWriter(directory, cfg);
			indexWriter.deleteDocuments(term);
			indexWriter.commit();
			indexWriter.close();
			System.out.println("删除完成!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入索引
	 */
	public static void createIndex() throws Exception{
		List<Book> list = new ArrayList<>();
		list.add(new Book(1, "java 编程思想", 71.5f, "28488.jpg", "作者简介  Bruce Eckel，是MindView公司的总裁，该公司项客户..."));
		list.add(new Book(1, "apache lucene", 66, "32458.jpg", "lucene是apache的开源项目，是一个全文检索的工具包..."));
		list.add(new Book(1, "mybatis", 55, "78463.jpg", "MyBatis介绍Mybatis本是apache的一个开源项目..."));
		list.add(new Book(1, "spring", 56, "96473.jpg", "## Spring Frameworkspringmvc.txt The Spring Frameworks..."));
		list.add(new Book(1, "solr", 78, "364179.jpg", "solr是一个全文检索服务# Licensed to the Apache Software..."));
		
		// 将采集到的数据封装到Document对象中
		List<Document> docList = new ArrayList<Document>();
		Document document;
		for (Book book : list) {
			document = new Document();
			// store:如果是yes，则说明存储到文档域中
			// 图书ID
			// Field id = new TextField("id", book.getId().toString(), Store.YES);
			
			Field id = new TextField("id", Integer.toString(book.getId()), Field.Store.YES);
			// 图书名称
			Field name = new TextField("name", book.getName(), Field.Store.YES);
			// 图书价格
			Field price = new TextField("price", Float.toString(book.getPrice()), Field.Store.YES);
			// 图书图片地址
			Field pic = new TextField("pic", book.getPic(), Field.Store.YES);
			// 图书描述
			Field description = new TextField("description", book.getDescription(), Field.Store.YES);
			
			// 将field域设置到Document对象中
			document.add(id);
			document.add(name);
			document.add(price);
			document.add(pic);
			document.add(description);

			docList.add(document);
		}
		// JDK 1.7以后 open只能接收Path/////////////////////////////////////////////////////
		// 创建分词器，标准分词器
		Analyzer analyzer = new StandardAnalyzer();
		
		// 创建IndexWriter
		// IndexWriterConfig cfg = new IndexWriterConfig(Version.LUCENE_6_5_0,analyzer);
		IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
		
		// 指定索引库的地址
		// File indexFile = new File("D:\\L\a\Eclipse\\lecencedemo\\");
		// Directory directory = FSDirectory.open(indexFile);
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(PATH));
		
		IndexWriter writer = new IndexWriter(directory, cfg);
		writer.deleteAll(); // 清除以前的index
		// 通过IndexWriter对象将Document写入到索引库中
		for (Document doc : docList) {
			writer.addDocument(doc);
		}
		// 关闭writer
		writer.close();
	}
	
	/**
	 * 执行条件查询
	 * @param query
	 */
	public static void doSearch(Query query) {
		// 创建IndexSearcher
		// 指定索引库的地址
		try {
			// Directory directory = FSDirectory.open(indexFile);
			// 1、创建Directory
			// JDK 1.7以后 open只能接收Path
			Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(PATH));
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);
			// 通过searcher来搜索索引库
			// 第二个参数：指定需要显示的顶部记录的N条
			TopDocs topDocs = searcher.search(query, 10);
			
			// 根据查询条件匹配出的记录总数
			int count = topDocs.totalHits;
			System.out.println("匹配出的记录总数:" + count);
			// 根据查询条件匹配出的记录
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			for (ScoreDoc scoreDoc : scoreDocs) {
				// 获取文档的ID
				int docId = scoreDoc.doc;
				// 通过ID获取文档
				Document doc = searcher.doc(docId);
				System.out.println("商品ID：" + doc.get("id"));
				System.out.println("商品名称：" + doc.get("name"));
				System.out.println("商品价格：" + doc.get("price"));
				System.out.println("商品图片地址：" + doc.get("pic"));
				System.out.println("商品描述：" + doc.get("description"));
				System.out.println("==========================");
				// System.out.println("商品描述：" + doc.get("description"));
			}
			// 关闭资源
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

/**
 * 实体
 * @Description: 
 * @author lianliang
 * @date 2017年10月17日 下午5:39:35
 */
class Book {
	private int id;
	private String name;
	private float price;
	private String pic;
	private String description;
	
	public Book(int id, String name, float price, String pic, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
