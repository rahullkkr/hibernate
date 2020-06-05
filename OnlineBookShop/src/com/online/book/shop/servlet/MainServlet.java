package com.online.book.shop.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.book.shop.action.AddBookAction;
import com.online.book.shop.action.AddBookToCartAction;
import com.online.book.shop.action.AllOrdersAction;
import com.online.book.shop.action.CancelEditInfoAction;
import com.online.book.shop.action.ChangePasswordAction;
import com.online.book.shop.action.DeleteBookAction;
import com.online.book.shop.action.EditInfoAction;
import com.online.book.shop.action.ForgetPasswordAction;
import com.online.book.shop.action.LoginAction;
import com.online.book.shop.action.LogoutAction;
import com.online.book.shop.action.NextAction;
import com.online.book.shop.action.PlaceOrderAction;
import com.online.book.shop.action.PreviousAction;
import com.online.book.shop.action.RegisterAction;
import com.online.book.shop.action.RemoveFromCartAction;
import com.online.book.shop.action.SearchBookAction;
import com.online.book.shop.action.ShowOrderInfoAction;
import com.online.book.shop.action.ShowUserInfoAction;
import com.online.book.shop.action.SortBookInfoAction;
import com.online.book.shop.action.UpdateInfoAction;
import com.online.book.shop.action.UpdateOrderStatusAction;
import com.online.book.shop.action.UserOrderStatusAction;

public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = -5107127927891382391L;
	
	LoginAction loginAction = null;
	LogoutAction logoutAction = null;
	ChangePasswordAction changePasswordAction = null;
	AddBookAction addBookAction = null;
	SearchBookAction searchBookAction = null;
	SortBookInfoAction sortBookInfoAction =null;
	ForgetPasswordAction forgetPasswordAction = null;
	RegisterAction registerAction = null;
	DeleteBookAction deleteBookAction = null;
	NextAction nextAction = null;
	PreviousAction previousAction = null;
	AddBookToCartAction addBookToCartAction = null;
	RemoveFromCartAction removeFromCartAction = null;
	PlaceOrderAction placeOrderAction = null;
	EditInfoAction editInfoAction =null;
	CancelEditInfoAction cancelEditInfoAction = null;
	UpdateInfoAction updateInfoAction = null;
	AllOrdersAction allOrdersAction = null;
	UserOrderStatusAction userOrderStatusAction = null;
	UpdateOrderStatusAction updateOrderStatusAction = null;
	ShowOrderInfoAction showOrderInfoAction = null;
	ShowUserInfoAction showUserInfoAction = null;
	
	public MainServlet() {
			System.out.println("Servlet Def Cons.......");
}
	
	public void init(ServletConfig config) throws ServletException {

		loginAction = new LoginAction();
		logoutAction =new LogoutAction();
		changePasswordAction = new ChangePasswordAction();
		addBookAction = new AddBookAction();
		searchBookAction = new SearchBookAction();
		sortBookInfoAction = new SortBookInfoAction();
		 forgetPasswordAction = new ForgetPasswordAction();
		 registerAction = new RegisterAction();
		 deleteBookAction = new DeleteBookAction();
		 nextAction = new NextAction();
		 previousAction = new PreviousAction();
		 addBookToCartAction = new AddBookToCartAction();
		 removeFromCartAction = new RemoveFromCartAction();
		 placeOrderAction = new PlaceOrderAction();
		 editInfoAction =new EditInfoAction();
		 cancelEditInfoAction = new CancelEditInfoAction();
		 updateInfoAction = new UpdateInfoAction();
		 allOrdersAction = new AllOrdersAction();
		 userOrderStatusAction = new UserOrderStatusAction();
		 updateOrderStatusAction = new UpdateOrderStatusAction();
		 showOrderInfoAction = new ShowOrderInfoAction();
		 showUserInfoAction = new ShowUserInfoAction();
		 System.out.println("Servlet init() method.......");
		
	}
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String uri =req.getRequestURI();
		String page="";
		
		if(uri.endsWith("login.do")){
			page=loginAction.verifyUser(req, res);
		}else if(uri.endsWith("logout.do")){
			page=logoutAction.logout(req, res);
		}else if(uri.endsWith("changePassword.do")){
			page=changePasswordAction.changepassword(req, res);
		}else if(uri.endsWith("addBook.do")){
			page=addBookAction.addBookInfo(req, res);
		}else if(uri.endsWith("searchBook.do")){
			page=searchBookAction.searchBookInfo(req, res);
		}else if(uri.endsWith("sortInfo.do")){
			page=sortBookInfoAction.sortBookInfo(req, res);
		}else if(uri.endsWith("forgetPassword.do")){
			page=forgetPasswordAction.searchPassword(req, res);
		}else if(uri.endsWith("register.do")){
			page=registerAction.registerStudent(req, res);
		}else if(uri.endsWith("next.do")){
			page=nextAction.searchBookInfo(req, res);
		}else if(uri.endsWith("previous.do")){
			page=previousAction.searchBookInfo(req, res);
		}else if(uri.endsWith("delete.do")){
			page=deleteBookAction.deleteBook(req, res);
		}else if(uri.endsWith("addtocart.do")){
			page=addBookToCartAction.addBookToCart(req, res);
		}else if(uri.endsWith("removefromcart.do")){
			page=removeFromCartAction.removeFromCart(req, res);
		}else if(uri.endsWith("placeorder.do")){
			page=placeOrderAction.placeOrder(req, res);
		}else if(uri.endsWith("editinfo.do")){
			page=editInfoAction.makeEditable(req, res);
		}else if(uri.endsWith("canceledit.do")){
			page=cancelEditInfoAction.cancelEditInfo(req, res);
		}else if(uri.endsWith("updateinfo.do")){
			page=updateInfoAction.updateUserInfo(req, res);
		}else if(uri.endsWith("allorders.do")){
			page=allOrdersAction.getAllOrders(req, res);
		}else if(uri.endsWith("userorderstatus.do")){
			page=userOrderStatusAction.getUserOrderStatus(req, res);
		}else if(uri.endsWith("updatestatus.do")){
			page=updateOrderStatusAction.updateOrderStatus(req, res);
		}else if(uri.endsWith("showorderinfo.do")){
			page=showOrderInfoAction.getOrderInfo(req, res);
		}else if(uri.endsWith("showuserinfo.do")){
			page=showUserInfoAction.getUserInfo(req, res);
		}
		
		req.getRequestDispatcher(page).forward(req, res);
		System.out.println("Servlet service()........");
	}
}
