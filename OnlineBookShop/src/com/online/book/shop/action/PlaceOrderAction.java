package com.online.book.shop.action;

import java.util.Calendar;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.online.book.shop.delegate.OrderDelegate;
import com.online.book.shop.to.OrderTO;
import com.online.book.shop.to.UserTO;
import com.online.book.shop.util.GetTotalAmount;
import com.online.book.shop.validator.JLCDataValidator;
public class PlaceOrderAction {

	public String placeOrder(HttpServletRequest req, HttpServletResponse res){
		
		String page="placeOrderDef.jsp";
		boolean isError=false;
		String address = req.getParameter("address");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String zip = req.getParameter("zip");
		String cardNo = req.getParameter("cardNo");
		String pin = req.getParameter("pin");
		String cardExp = req.getParameter("cardExp");
		
		//VALIDATING ADDRESS
		
		if(! JLCDataValidator.validateRequired(address)){
			req.setAttribute("address", "Address is Required.");
			isError = true;
		}else if(! JLCDataValidator.minLength(address, 3)){
			req.setAttribute("address", "Address must be of minimum 3 characters");
			isError = true;
		}else if(! JLCDataValidator.maxLength(address, 50)){
			req.setAttribute("address", "Address must be of maximum  50 characters");
			isError = true;
		}
		
		//VALIDATING STREET
		
				if(! JLCDataValidator.validateRequired(street)){
					req.setAttribute("street", "Street is Required.");
					isError = true;
				}else if(! JLCDataValidator.minLength(street, 3)){
					req.setAttribute("street", "Street must be of minimum 3 characters");
					isError = true;
				}else if(! JLCDataValidator.maxLength(street, 50)){
					req.setAttribute("street", "Street must be of maximum  50 characters");
					isError = true;
				}
		
				//VALIDATING CITY
				
				if(! JLCDataValidator.validateRequired(city)){
					req.setAttribute("city", "City is Required.");
					isError = true;
				}else if(! JLCDataValidator.minLength(city, 3)){
					req.setAttribute("city", "City must be of minimum 3 characters");
					isError = true;
				}else if(! JLCDataValidator.maxLength(city, 30)){
					req.setAttribute("city", "City must be of maximum  30 characters");
					isError = true;
				}
				
				//VALIDATING STATE
				
				if(! JLCDataValidator.validateRequired(state)){
					req.setAttribute("state", "State is Required.");
					isError = true;
				}else if(! JLCDataValidator.minLength(state, 2)){
					req.setAttribute("state", "State must be of minimum 2 characters");
					isError = true;
				}else if(! JLCDataValidator.maxLength(state, 20)){
					req.setAttribute("state", "State must be of maximum  20 characters");
					isError = true;
				}
				
				//VALIDATING COUNTRY
				
				if(! JLCDataValidator.validateRequired(country)){
					req.setAttribute("country", "Country is Required.");
					isError = true;
				}else if(! JLCDataValidator.minLength(country, 2)){
					req.setAttribute("country", "Country must be of minimum 2 characters");
					isError = true;
				}else if(! JLCDataValidator.maxLength(country, 20)){
					req.setAttribute("country", "Country must be of maximum  20 characters");
					isError = true;
				}
				
				//VALIDATING ZIP
				
				if(! JLCDataValidator.validateRequired(zip)){
					req.setAttribute("zip", "Zip is Required.");
					isError = true;
				}else if(! JLCDataValidator.validateLong(zip)){
					req.setAttribute("zip", "Zip must be in digits only.");
					isError = true;
				}else if(! JLCDataValidator.minLength(zip, 6)){
					req.setAttribute("zip", "Zip must be of 6 digits");
					isError = true;
				}else if(! JLCDataValidator.maxLength(zip, 6)){
					req.setAttribute("zip", "Zip must be of 6 digits");
					isError = true;
				}
				
				//VALIDATING CARD NUMBER
				
				if(! JLCDataValidator.validateRequired(cardNo)){
					req.setAttribute("cardNo", "Card No. is Required.");
					isError = true;
				}else if(! JLCDataValidator.validateLong(cardNo)){
					req.setAttribute("cardNo", "Card No. must be in digits only.");
					isError = true;
				}else if(! JLCDataValidator.minLength(cardNo, 16)){
					req.setAttribute("cardNo", "Card No. must be minimum of 16 digits");
					isError = true;
				}else if(! JLCDataValidator.maxLength(cardNo, 16)){
					req.setAttribute("cardNo", "Zip must be maximum of 16 digits");
					isError = true;
				}
				
				//VALIDATING SECRET PIN
				
				if(! JLCDataValidator.validateRequired(pin)){
					req.setAttribute("pin", "Pin No. is Required.");
					isError = true;
				}else if(! JLCDataValidator.validateLong(pin)){
					req.setAttribute("pin", "Pin No. must be in digits only.");
					isError = true;
				}else if(! JLCDataValidator.minLength(pin, 4)){
					req.setAttribute("pin", "Pin No. must be minimum of 16 digits");
					isError = true;
				}else if(! JLCDataValidator.maxLength(pin, 4)){
					req.setAttribute("pin", "Pin must be maximum of 16 digits");
					isError = true;
				}
				
				//VALIDATING EXPIRY DATE
				
				if(! JLCDataValidator.validateRequired(cardExp)){
					req.setAttribute("cardExp", "Expiry date is Required.");
					isError = true;
				}else if(! JLCDataValidator.minLength(cardExp, 6)){
					req.setAttribute("cardExp", "Not a valid Expiry date.");
					isError = true;
				}else if(! JLCDataValidator.maxLength(cardExp, 7)){
					req.setAttribute("cardExp", "Not a valid Expiry date.");
					isError = true;
				}else if(! JLCDataValidator.validateExpDate(cardExp)){
					req.setAttribute("cardExp", "Not a valid Expiry date.");
					isError = true;
				}
				
				if(! isError){
					String orderDate = Calendar.getInstance().get(Calendar.DATE)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR);

					Set orderItemList = (Set) req.getSession().getAttribute("SELECTED_BOOK_LIST");
					
					float totalAmount = 0.0f;
					int totalItem = 0;
					Object obj = req.getSession().getAttribute("TOTAL_BOOK_QUANTITY");
					if(obj != null){
						Integer i = (Integer)obj;
						totalItem=i.intValue();
					}
					obj = req.getSession().getAttribute("TOTAL_BOOK_AMOUNT");
					if(obj != null){
						Double d= (Double)obj;
						totalAmount = Float.parseFloat(GetTotalAmount.getTotalAmount(d.toString()));
					}
					UserTO userTo = (UserTO)req.getSession().getAttribute("USER_TO");
					OrderTO oto = new OrderTO();
					oto.setAddress(address);
					oto.setCardNo(cardNo);
					oto.setCity(city);
					oto.setCountry(country);
					oto.setExpDate(cardExp);
					oto.setUserId(userTo.getUserId());
					oto.setOrderItemList(orderItemList);
					oto.setState(state);
					oto.setStreet(street);
					oto.setTotalAmount(totalAmount);
					oto.setTotalItem(totalItem);
					oto.setZip(zip);
					oto.setOrderDate(orderDate);
					String ip="127.0.0.1";
					String orderId = OrderDelegate.placeOrder(oto, ip);   //ip = req.getRemoteAddr()
					if(orderId != null){
						req.setAttribute("ORDER_PLACED", orderId);
						HttpSession sess = req.getSession();
						sess.removeAttribute("TOTAL_BOOK_AMOUNT");
						sess.removeAttribute("TOTAL_BOOK_QUANTITY");
					}else{
						req.setAttribute("errorInOrder", "Error Occured while placing order. Please try later.");
					}
				}
				return page;
}
}
