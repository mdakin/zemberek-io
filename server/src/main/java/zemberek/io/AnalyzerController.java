package zemberek.io;

import java.io.IOException;
import java.util.List;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

@Controller
@EnableAutoConfiguration
public class AnalyzerController {
    static TurkishMorphology morphology;
    static {
        try {
            morphology = TurkishMorphology.createWithDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/analyze")
    @ResponseBody
    String home(@RequestParam(name="word", required=false, defaultValue="") String word) {
        List<WordAnalysis> kedicikler = morphology.analyze(word);
        StringBuilder sb = new StringBuilder(word);
        for (WordAnalysis analysis : kedicikler ) {
            sb.append("<div>");
            sb.append(analysis.formatLong());
            sb.append("</div>");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AnalyzerController.class, args);
    }
}