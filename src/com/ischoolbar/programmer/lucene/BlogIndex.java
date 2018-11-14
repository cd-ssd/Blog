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
 * ����������(������)
 *
 */
public class BlogIndex {

	private Directory dir=null;
	

	/**
	 * ��ȡIndexWriterʵ��
	 */
	private IndexWriter getWriter()throws Exception{
		dir=FSDirectory.open(Paths.get("C://lucene"));//���������ĵ�
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();//SmartChineseAnalyzer ��һ���������ķִ�ģ�飬 �ܹ����ø��ʶԺ�����ӽ��������з֣��ִ���)
		IndexWriterConfig iwc=new IndexWriterConfig(analyzer);//�����������
		IndexWriter writer=new IndexWriter(dir, iwc);//��������ȥ�����ĵ��в���
		return writer;
	}
	
	/**
	 * ��Ӳ�������
	 */
	public void addIndex(Blog blog)throws Exception{
		
		IndexWriter writer=getWriter();//��������ʵ��������ࣨIndexWriter�����𴴽����������ߴ������������Լ�����������ӡ�ɾ������±������ĵ�����Ϣ��������getWriter()��������ʱ�򷽷��ķ���ֵwriterҲ���Ǹ���IndexWriter writer=getWriter();�е�writer,ֻ��������ͬ���ѣ�)
		Document doc=new Document();//Document�������һЩ��Field���ļ��ϡ��ĵ���������ĵ������ĵ���ص�һЩԪ���ݡ�Ԫ���ݣ������ߡ����⡢������޸����ڵȣ�����Ϊ�ĵ��Ĳ�ͬ�򵥶��洢����������
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));//��ʾ���������е�������ȫ�洢���ļ��У���id���浽doc��)
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));//��ʾ���������е�������ȫ�洢���ļ��У���title���浽doc��)
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));//��ʾ���������е�������ȫ�洢���ļ��У������ڴ��浽doc��)
		doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));//��ʾ���������е�������ȫ�洢���ļ��У������ݴ��浽doc��)
		writer.addDocument(doc);//���ʵ�ʵ����ݴ洢����
		writer.close();//��д���ݹر�
	}
	
	/**
	 * ���²�������
	 */
	public void updateIndex(Blog blog)throws Exception{
		IndexWriter writer=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
		writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);// ��������ʹ��Ĭ�Ϸִ��� (����id)
		writer.close();
	}
	
	/**
	 * ɾ��ָ�����͵�����
	 */
	public void deleteIndex(String blogId)throws Exception{
		IndexWriter writer=getWriter();//IndexWriter���ڸ��»򴴽�������������������ȡ������
		writer.deleteDocuments(new Term("id",blogId));
		writer.forceMergeDeletes(); // ǿ��ɾ��
		writer.commit();
		writer.close();
	}
	
	/**
	 * ��ѯ������Ϣ
	 */
	public List<Blog> searchBlog(String q)throws Exception{
		dir=FSDirectory.open(Paths.get("C://lucene"));//��������������ŵ�λ����Ϣ    ����"c://lucene" �Ǵ���������ļ���λ��.
		IndexReader reader = DirectoryReader.open(dir);//��ֻ��ȡ�����ĵ�. 
		IndexSearcher is=new IndexSearcher(reader);//Ȼ�󽻸���������IndexSearcher ȥ��ɲ���
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();//����������ѯ��������BooleanQuery
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();//���ķִ�
		QueryParser parser=new QueryParser("title",analyzer);//ʹ��QueryParser��ɽ��������������������ڡ�title��(�ֶ�����)�ֶ��а����С�analyzer�����ĵ�������ʹ��QueryParser���в�ѯʱ��Ϊ���ϲ�ѯ�����Ľ�����ء�
		Query query=parser.parse(q);//��������ؼ���q���Բ�ѯ�������﷨������ //���������������
		QueryParser parser2=new QueryParser("content",analyzer);//��"content"���зִʲ����������ϵ����ݷ���
		Query query2=parser2.parse(q);//��������ؼ���q
		booleanQuery.add(query,BooleanClause.Occur.SHOULD);//SHOULD��ʾ���򡱹�ϵ�����ռ������queryΪ���м����Ӿ�Ĳ���
		booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
		TopDocs hits=is.search(booleanQuery.build(), 100);////��������ѯ��ȡǰ100����¼     TopDocs ָ����ƥ�������������ǰN(Ҳ����100)���������   //�������ƶ���ߵ�100����¼
		QueryScorer scorer=new QueryScorer(query);  //���ݵ÷�����,��Ҫ�Ա���÷�����  ������query����
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);  //����Fragmenter����,�����ĵ���Ƭ    ��ԭʼ�ַ������ݵ÷ֲ�ֳɶ�����Ƭ�Ρ�
		SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");//���������Ҫ�������ֶ��ò�ͬ����ʽ����ɫ����ʾ������
		Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);////��ѯһ��Lucen�Ĳ�ѯ�����Ѳ�ѯ������ָ��λ�ü����趨�ĸ�����ʽ
		highlighter.setTextFragmenter(fragmenter);  //���ú��йؼ����ı���Ĵ�С
		List<Blog> blogList=new LinkedList<Blog>();
		for(ScoreDoc scoreDoc:hits.scoreDocs){//�����÷�ǰ100�ļ�¼ 
			Document doc=is.doc(scoreDoc.doc);//����ƥ���ı�
			Blog blog=new Blog();
			blog.setId(Integer.parseInt(doc.get(("id"))));
			blog.setReleaseDateStr(doc.get(("releaseDate")));
			String title=doc.get("title");
			String content=StringEscapeUtils.escapeHtml(doc.get("content"));//ת��HTML
			if(title!=null){//������ⲻΪ�գ��жϱ������Ƿ��в�ѯ�ֶΣ�������ѯ�ֶθ�����ʾ  
				TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));//��ʾ��analyzer.tokenStream�����Ҫ�ִʵ�title���д���Ȼ�󷵻�һ��TokenStream����  //�ִ������ô���֮��õ���һ����,������д洢�˷ִʵĸ�����Ϣ
				String hTitle=highlighter.getBestFragment(tokenStream, title);//������ʾ��ȡ��title
				if(StringUtil.isEmpty(hTitle)){
//					blog.setTitle(title);
				}else{
					blog.setTitle(hTitle);	//����ѯ(title)�ֶθ�����ʾ				
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
		return blogList;//��ǰ�˵���
	}
}
