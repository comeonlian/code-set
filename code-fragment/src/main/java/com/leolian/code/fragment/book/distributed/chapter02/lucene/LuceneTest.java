package com.leolian.code.fragment.book.distributed.chapter02.lucene;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.WildcardQuery;
import org.junit.Test;

public class LuceneTest {
	
	@Test
	public void wildcardQuery() {
		// 通配符查询
		Term term = new Term("name", "s*");
		WildcardQuery query = new WildcardQuery(term);
		
		LuceneDemo03.doSearch(query);
	}
	
	
	@Test
	public void multiQuery() {
		// 组合查询
		PhraseQuery.Builder builder1 = new PhraseQuery.Builder();
		builder1.add(new Term("description", "Spring"));
		builder1.add(new Term("description", "Spring"));
		builder1.setSlop(10);
		PhraseQuery phraseQuery = builder1.build();
		
		Term term = new Term("name", "s*");
		WildcardQuery wildcardQuery = new WildcardQuery(term);
		
		BooleanQuery.Builder builder2 = new BooleanQuery.Builder();
		builder2.add(phraseQuery, BooleanClause.Occur.SHOULD);
		builder2.add(wildcardQuery, BooleanClause.Occur.SHOULD);
		BooleanQuery booleanQuery = builder2.build();
		
		LuceneDemo03.doSearch(booleanQuery);
	}
	
}
