package sg.edu.nus.iss.app.workshop12.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import sg.edu.nus.iss.app.workshop12.exception.RandNumberException;
import sg.edu.nus.iss.app.workshop12.models.Generate;

/*
 * Redirect to the generate.html
 */
@Controller
@RequestMapping(path = "/rand")
public class GenRandNoController {

    /*
     * Redirect to the generate.html and show the input form
     */
    @GetMapping(path = "/show")
    public String showRandForm(Model model) {

        // Instantiate the generate object
        // bind the noOfRandNo to the text field
        Generate g = new Generate();

        // associate the bind var to the view/page
        model.addAttribute("generateObj", g);
        return "generate";
    }

    @PostMapping(path = "/generate")
    public String postRandNum(@ModelAttribute Generate generate, Model model) {
        this.randomizeNum(model, generate.getNumberVal());
        return "result";
    }

    private void randomizeNum(Model model, int noOfGenerateNo) {
        int maxGenNo = 30;
        String[] imgNumbers = new String[maxGenNo + 1]; // because 31 image files

        // validate only accept > 0 and <=30
        if (noOfGenerateNo < 1 || noOfGenerateNo > maxGenNo) {
            throw new RandNumberException();
        }

        // generate all the number images file name store it
        // to the imgNumbers array
        for (int i = 0; i < maxGenNo + 1; i++) {
            imgNumbers[i] = "number" + i + ".jpg";
        }

        List<String> selectedImg = new ArrayList<>();
        Random rnd = new Random();

        // generated number needs to be unique at every generation
        Set<Integer> uniqueResult = new LinkedHashSet<>();
        while (uniqueResult.size() < noOfGenerateNo) {
            Integer resultOfRand = rnd.nextInt(maxGenNo);
            uniqueResult.add(resultOfRand);
        }

        // to iterate the uniqueResult to get image file name.
        // then display in selectedImg
        Iterator<Integer> it = uniqueResult.iterator();
        Integer currElem = null;
        while (it.hasNext()) {
            currElem = it.next();
            selectedImg.add(imgNumbers[currElem.intValue()]);
        }

        model.addAttribute("numberRandomNum", noOfGenerateNo);
        model.addAttribute("randNumResult", selectedImg.toArray());
    }
}
