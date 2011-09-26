package controllers.buyer;

import java.util.List;

import models.Address;
import models.Country;
import models.Favorite;
import models.Message;
import models.User;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.mvc.Controller;

/**
 * Buyer's Internal Messages管理
 * 
 * @author ray
 * 
 */
public class Messages extends Controller {

	/**
	 * 查询
	 */
	public static void index() {
		List<Message> mails = Message.find(
				"user=? and userdel=0 order by id desc",
				Member.getSessionUser()).fetch();
		render(mails);
	}

	/**
	 * 发送内部消息
	 * 
	 * @param id
	 */
	public static void send(String username) {
		User user = User.findByUsername(username);
		notFoundIfNull(user);
		render(username);
	}

	/**
	 * 保存信息
	 * 
	 * @param address
	 */
	public static void save(@Valid Message message, @Required String username) {
		if (validation.hasErrors()) {
			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			send(username);
		}
		User sender = Member.getSessionUser();
		User user = User.findByUsername(username);
		if (sender.id.equals(user.id)) {
			flash.error("Sorry,You can't send message to your self");
			params.flash(); // add http parameters to the flash scope
			send(username);			
		}
		message.user = user;
		message.sender = Member.getSessionUser();

		message.save();

		flash.success("Message sent successfully!");
		index();
	}

	public static void delete(Long id) {
		Message message = Message.findById(id);
		notFoundIfNull(message);
		User user = Member.getSessionUser();
		if (message.user.id.equals(user.id)) {
			message.userdel = 1;
		} else if (message.sender.id.equals(user.id)) {
			message.senderdel = 1;
		} else {
			notFound();
		}
		message.save();

		index();
	}
}
