for web page to start at generate.html ->

at GenRandNoController.java ---> replace below section of code

@Controller
public class GenRandNoController {

  
    @GetMapping(path = "/")
    public String showRandForm(Model model) {

        Generate g = new Generate();
        model.addAttribute("generateObj", g);
        return "generate";
    }

    @PostMapping(path = "/generate")
    public String postRandNum(@ModelAttribute Generate generate, Model model) {
        this.randomizeNum(model, generate.getNumberVal());
        return "result";
    }

==================================================================
at generate.html ---> repalce below section of code

<form
      action="#"
      th:action="@{/generate}"
      th:object="${generateObj}"
      method="post"
    >
      <p>
        <input type="text" th:field="*{numberVal}" />
        <input type="submit" value="Generate" />
      </p>
    </form>