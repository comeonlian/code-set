package com.leolian.code.fragment.book.distributed.chapter02.lucene;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;

/**
 * Lucene 6.0，示例程序，分词技术
 * 
 * @Description:
 * @author lianliang
 * @date 2017年10月17日 下午5:11:20
 */
public class LuceneDemo02 {
	static Analyzer analyzer = null;
	static Directory directory = null;
	static String text = "CSDN.NET - 全球最大中文IT社区，为IT专业技术人员提供最全面的信息传播和服务平台";
	static String text1 = "京华时报1月23日报道 昨天，受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。";

	public static void main(String[] args) throws Exception {
		analyzer = new ComplexAnalyzer();
		directory = new RAMDirectory();
		IndexWriterConfig iwConfig = new IndexWriterConfig(analyzer);
		iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		IndexWriter iwriter = new IndexWriter(directory, iwConfig);
		List<String> list = new ArrayList<String>();
		list.add(text);
		list.add(text1);
		for (String item : list) {
			Document doc = new Document();
			doc.add(new TextField("text", item, Field.Store.YES));
			iwriter.addDocument(doc);
		}
		iwriter.close();
		
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(ireader);
		Query q = new TermQuery(new Term("text", "西伯利亚"));
		System.out.println(q);
		TopDocs tds = searcher.search(q, 10);
		System.out.println("======size:" + tds.totalHits + "========");
		for (ScoreDoc sd : tds.scoreDocs) {
			System.out.println(sd.score);
			System.out.println(searcher.doc(sd.doc).get("text"));
		}
	}
}
