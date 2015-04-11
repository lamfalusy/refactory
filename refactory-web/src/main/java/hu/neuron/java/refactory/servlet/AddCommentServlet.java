package hu.neuron.java.refactory.servlet;

import hu.neuron.java.refactory.serializer.DateSerializer;
import hu.neuron.java.refactory.service.DBMock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.neuron.java.refactory.vo.CommentVO;
import hu.neuron.java.refactory.vo.TicketVO;

/**
 * Servlet implementation class AddCommentServlet
 */
//@WebServlet(name = "AddCommentServlet", urlPatterns = { "/secured/AddCommentServlet" })
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<Long, TicketVO> db = DBMock.getDb();
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).create();
		
		TicketVO ticket = db.get(new Long(request.getParameter("id")));
		
		List<CommentVO> comments = ticket.getComments();
		
		if (comments == null) {
			comments = new ArrayList<CommentVO>();
		}
		
		String jsonRequest = request.getParameter("comment");

		CommentVO comment = new Gson().fromJson(jsonRequest, CommentVO.class);
		comment.setAdded(new Date());

		comments.add(comment);
		
		Collections.sort(comments, Collections.reverseOrder());
		
		response.setCharacterEncoding("UTF-8");
		gson.toJson(comments, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
