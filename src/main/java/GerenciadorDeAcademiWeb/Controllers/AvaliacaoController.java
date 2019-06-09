package GerenciadorDeAcademiWeb.Controllers;

import GerenciadorDeAcademiWeb.Models.Avaliacao;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AvaliacaoController {
    
    @Value("${api.baseUrl}")
    private String baseUrl;

    @RequestMapping(value = {"/avaliacao/{idAluno}"})
    public ModelAndView avaliacao(@Valid @PathVariable("idAluno") UUID idAluno){
        ModelAndView modelAndView = new ModelAndView("Avaliacao/Avaliacao");                
        
        List<Avaliacao> avaliacoes = GetAvaliacoes(idAluno);
        
        modelAndView.addObject("avaliacoes", avaliacoes);                
        modelAndView.addObject("idAluno", idAluno);                
        return modelAndView;
    }    
    
    @RequestMapping(value = {"/avaliar/{id}"})
    public ModelAndView avaliar(@Valid @PathVariable("idAluno") UUID idAluno){                        
        ModelAndView modelAndView = new ModelAndView("Avaliacao/Avaliacao");
        modelAndView.addObject("idAluno", idAluno);
        return modelAndView;             
    }
    
    private List<Avaliacao> GetAvaliacoes(UUID idAluno){
        return new ArrayList<Avaliacao>();
    }
}
