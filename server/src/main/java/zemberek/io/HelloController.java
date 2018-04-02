package zemberek.io;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

@RestController
public class HelloController {
    static TurkishMorphology morphology;
    static {
        try {
            morphology = TurkishMorphology.createWithDefaults();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public String index() {
        List<WordAnalysis> kedicikler = morphology.analyze("kedicikler");
        StringBuilder sb = new StringBuilder("kedicikler:");
        for (WordAnalysis analysis : kedicikler ) {
            sb.append("<div>");
            sb.append(analysis.formatLong());
            sb.append("</div>");
        }
        return sb.toString();
    }
}
