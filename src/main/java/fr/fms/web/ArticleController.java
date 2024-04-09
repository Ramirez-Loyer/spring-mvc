package fr.fms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	
	//@RequestMapping(value= "/index", method=RequestMethod.GET)
	@GetMapping("/index")  //dans une servlet on utilisait request.getParameter("page")
	public String index(Model model, @RequestParam(name="page", defaultValue = "0") int page) { //le model est fourni par spring
		Page<Article> articles = articleRepository.findAll(PageRequest.of(page,  5));
		//en retour, au lieu d'un eliste d'articles, on a tous les articles formatés en page pointant sur la page demandée
 		//List<Article> articles = articleRepository.findAll(); //récupère tous les articles
		model.addAttribute("listArticle", articles.getContent());   //Pour récupérer sous forme de liste la page pointée
		
		
		return "articles";  //cette methode retourne au dispacterServlet une vue
	}
}
