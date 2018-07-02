package com.meireles.financeiro.filter;

import com.meireles.finaneiro.controller.Usuario;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*.xhtml")
public class AutorizacaoFilter implements javax.servlet.Filter {

    @Inject
    private Usuario usuario;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Verifica se usuario está logado, senao redireciona para tela de login.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(!usuario.isLogado() &&
                !request.getRequestURI().endsWith("/Login.xhtml") &&
                !request.getRequestURI().contains(
                        "/javax.faces.resource/")){

                response.sendRedirect(request.getContextPath() + "/Login.xhtml");

        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
