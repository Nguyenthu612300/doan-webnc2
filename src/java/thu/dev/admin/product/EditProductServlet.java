/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thu.dev.admin.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import thu.dev.BaseServlet;
import thu.dev.admin.BaseAdminServlet;
import thu.dev.data.dao.CategoryDao;
import thu.dev.data.dao.DatabaseDao;
import thu.dev.data.dao.ProductDao;
import thu.dev.data.dao.model.Category;
import thu.dev.data.dao.model.Product;

/**
 *
 * @author User
 */
public class EditProductServlet extends BaseAdminServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Product product = productDao.find(productId);
        
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("product", product);
        request.getRequestDispatcher("admin/products/edit.jsp").include(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException{
//        super.doGet(req, resp);
        int productId = Integer.parseInt(req.getParameter("productId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Product product = productDao.find(productId);
        
        String name = req.getParameter("name");
        String img = req.getParameter("img");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        
        product.setName(name);
        product.setImg(img);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategoryId(categoryId);
        
       productDao.update(product);
        
         response.sendRedirect("IndexProductServlet");
    }
}


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
