package com.demo.blog;

import com.demo.common.model.Blog;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class BlogValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("blog.title", "titleMsg", "请输入Blog标题!");
		validateRequiredString("blog.content", "contentMsg", "请输入Blog内容!");
	}
	
	protected void handleError(Controller controller) {
		//把修改显示的内容全部删除提交空数据提示请输入标题和内容，再输入内容提交若无keepModel报错，若有能继续正常修改。
		controller.keepModel(Blog.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/blog/save"))
			controller.render("/blog/add.html");
		else if (actionKey.equals("/blog/update"))
			controller.render("/blog/edit.html");
	}
}
