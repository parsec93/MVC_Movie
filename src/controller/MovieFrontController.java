package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.LoginProAction;
import action.LogoutProAction;
import action.MemberInfoAction;
import action.MemberJoinProAction;
import action.MovieInfoDetailProAction;
import action.MovieListAction;
import action.MovieReservationAction;
import action.MovieReservationProAction;
import vo.ActionForward;

@WebServlet("*.mo")
public class MovieFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MovieFrontController");
		
		
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/MovieList.mo")) {
			action = new MovieListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MovieInfoDetail.mo")) {
			action = new MovieInfoDetailProAction();
			try {
				forward = action.execute(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MovieReservation.mo")) {
			action = new MovieReservationAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MovieReservationPro.mo")) {
            action = new MovieReservationProAction();
            
            try {
                forward = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		
		
		
		
		
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
