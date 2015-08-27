

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bullhorn;
import customTools.DBUtil;

/**
 * Servlet implementation class InsertPost
 */
@WebServlet("/InsertPost")
public class InsertPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			message="";
			message+="<h3>List Of Posts Entered:</h3>";
			//String name=request.getParameter("name");
			String post=request.getParameter("post");
			
			model.Bullhorn user=new model.Bullhorn();
			user.setName("Nisha");
			user.setPost(post);
			
			user.setPersonId((BigDecimal.valueOf((long)1)));
			
			if(!post.equalsIgnoreCase(""))	
			customTools.DBUtil.insert(user);
			EntityManager em=DBUtil.getEmFactory().createEntityManager();
			String q="select b from Bullhorn b order by b.postId desc";
			TypedQuery<Bullhorn>bq =em.createQuery(q,Bullhorn.class);
			List<Bullhorn> list=bq.getResultList();
			for(Bullhorn temp:list)
				message+=temp.getPost()+"<br>";
			request.setAttribute("message", message);
    		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
