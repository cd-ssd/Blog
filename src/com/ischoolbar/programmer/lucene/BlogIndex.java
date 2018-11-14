package com.ischoolbar.programmer.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.ischoolbar.programmer.entity.Blog;
import com.ischoolbar.programmer.util.DateUtil;
import com.ischoolbar.programmer.util.StringUtil;

/**
 * 博客索引类(搜索类)
 *
 */
public class BlogIndex {

	private Directory dir=null;
	

	/**
	 * 获取IndexWriter实例
	 */
	private IndexWriter getWriter()throws Exception{
		dir=FSDirectory.open(Paths.get("C://lucene"));//建立索引文档
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();//SmartChineseAnalyzer 是一个智能中文分词模块， 能够利用概率对汉语句子进行最优切分（分词器)
		IndexWriterConfig iwc=new IndexWriterConfig(analyzer);//保存参数配置
		IndexWriter writer=new IndexWriter(dir, iwc);//根据搜索去索引文档中查找
		return writer;
	}
	
	/**
	 * 添加博客索引
	 */
	public void addIndex(Blog blog)throws Exception{
		
		IndexWriter writer=getWriter();//创建索引实例，这个类（IndexWriter）负责创建新索引或者打开已有索引，以及向索引中添加、删除或更新被索引文档的信息（调用了getWriter()方法，这时候方法的返回值writer也就是给了IndexWriter writer=getWriter();中的writer,只是名字相同而已！)
		Document doc=new Document();//Document对象代表一些域（Field）的集合。文档的域代表文档或者文档相关的一些元数据。元数据（如作者、标题、主题和修改日期等）都作为文档的不同域单独存储并被索引。
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));//表示会把这个域中的内容完全存储到文件中（将id储存到doc中)
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));//表示会把这个域中的内容完全存储到文件中（将title储存到doc中)
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));//表示会把这个域中的内容完全存储到文件中（将日期储存到doc中)
		doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));//表示会把这个域中的内容完全存储到文件中（将内容储存到doc中)
		writer.addDocument(doc);//完成实际的数据存储工作
		writer.close();//读写数据关闭
	}
	
	/**
	 * 更新博客索引
	 */
	public void updateIndex(Blog blog)throws Exception{
		IndexWriter writer=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
		writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);// 更新索引使用默认分词器 (更新id)
		writer.close();
	}
	
	/**
	 * 删除指定博客的索引
	 */
	public void deleteIndex(String blogId)throws Exception{
		IndexWriter writer=getWriter();//IndexWriter用于更新或创建索引。它不是用来读取索引。
		writer.deleteDocuments(new Term("id",blogId));
		writer.forceMergeDeletes(); // 强制删除
		writer.commit();
		writer.close();
	}
	
	/**
	 * 查询博客信息
	 */
	public List<Blog> searchBlog(String q)throws Exception{
		dir=FSDirectory.open(Paths.get("C://lucene"));//用于描述索引存放的位置信息    其中"c://lucene" 是存放索引的文件夹位置.
		IndexReader reader = DirectoryReader.open(dir);//它只读取索引文档. 
		IndexSearcher is=new IndexSearcher(reader);//然后交给检索工具IndexSearcher 去完成查找
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();//“多条件查询”搜索—BooleanQuery
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();//中文分词
		QueryParser parser=new QueryParser("title",analyzer);//使用QueryParser完成解析搜索请求，这样所有在“title”(字段名称)字段中包含有“analyzer”的文档都会在使用QueryParser进行查询时作为符合查询条件的结果返回。
		Query query=parser.parse(q);//解析多个关键字q（对查询语句进行语法分析） //解析你输入的文字
		QueryParser parser2=new QueryParser("content",analyzer);//对"content"进行分词操作，将符合的内容返回
		Query query2=parser2.parse(q);//解析多个关键字q
		booleanQuery.add(query,BooleanClause.Occur.SHOULD);//SHOULD表示“或”关系，最终检索结果query为所有检索子句的并集
		booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
		TopDocs hits=is.search(booleanQuery.build(), 100);////多条件查询，取前100条记录     TopDocs 指向相匹配的搜索条件的前N(也就是100)个搜索结果   //搜索相似度最高的100条记录
		QueryScorer scorer=new QueryScorer(query);  //根据得分排序,主要以标题得分排序  ，传入query参数
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);  //构建Fragmenter对象,用于文档切片    将原始字符串根据得分拆分成独立的片段。
		SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");//高亮会把你要搜索的字段用不同的样式（红色）显示出来。
		Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);////查询一个Lucen的查询，并把查询出来的指定位置加上设定的高亮格式
		highlighter.setTextFragmenter(fragmenter);  //设置含有关键字文本块的大小
		List<Blog> blogList=new LinkedList<Blog>();
		for(ScoreDoc scoreDoc:hits.scoreDocs){//遍历得分前100的记录 
			Document doc=is.doc(scoreDoc.doc);//返回匹配文本
			Blog blog=new Blog();
			blog.setId(Integer.parseInt(doc.get(("id"))));
			blog.setReleaseDateStr(doc.get(("releaseDate")));
			String title=doc.get("title");
			String content=StringEscapeUtils.escapeHtml(doc.get("content"));//转义HTML
			if(title!=null){//如果标题不为空，判断标题中是否含有查询字段，并将查询字段高亮显示  
				TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));//表示用analyzer.tokenStream对这个要分词的title进行处理，然后返回一个TokenStream对象  //分词器做好处理之后得到的一个流,这个流中存储了分词的各种信息
				String hTitle=highlighter.getBestFragment(tokenStream, title);//高亮显示获取的title
				if(StringUtil.isEmpty(hTitle)){
//					blog.setTitle(title);
				}else{
					blog.setTitle(hTitle);	//将查询(title)字段高亮显示				
				}
			}
			if(content!=null){
				TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content)); 
				String hContent=highlighter.getBestFragment(tokenStream, content);
				if(StringUtil.isEmpty(hContent)){
					if(content.length()<=200){
						blog.setContent(content);
					}else{
						blog.setContent(content.substring(0, 200));						
					}
				}else{
					blog.setContent(hContent);					
				}
			}
			blogList.add(blog);
		}
		return blogList;//给前端调用
	}
}
