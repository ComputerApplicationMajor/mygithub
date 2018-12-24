package com.demo.blog;

import com.demo.common.model.Blog;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	public void index() {
		setAttr("blogPage", Blog.me.paginate(getParaToInt(0, 1), 3));
		render("blog.html");
	}
	
	public void add() {

	}
	
	@Before(BlogValidator.class)
	public void save() {
//		getModel(Blog.class).save();
		Blog blog = getModel(Blog.class);
//		System.out.println(blog.getId());
//		System.out.println(blog.getTitle());
//		System.out.println(blog.getContent());
//		blog.setTitle("jFinal");
		blog.setContent("sun");
		System.out.println(blog.getTitle());
		System.out.println(blog.getContent());
		blog.save();
		redirect("/blog");
	}
	
	@Before(BlogValidator.class)
	public void update() {

		getModel(Blog.class).update();
		redirect("/blog");
	}
	public void edit(){

		Blog blog = Blog.me.findById(getParaToInt());
		setAttr("blog", blog);
	}
	public void view(){
		Blog blog = Blog.me.findById(getParaToInt());
  		setAttr("blog", blog);
   }

	public void delete() {

		Blog.me.deleteById(getParaToInt());
		redirect("/blog");
	}
}


