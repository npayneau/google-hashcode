package com.thales.googlehashcode.hash2018;

import com.thales.googlehashcode.hash2018.model.Picture;
import com.thales.googlehashcode.hash2018.model.Slide;
import com.thales.googlehashcode.hash2018.model.SlideShow;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Log4j2
public class HashCodeSolution {

    private final List<String> output = new ArrayList<>();
    private SlideShow slideShow;
    private final int score = 0;

    public void run(final Scanner scanner) throws IOException {
        log.info("============== START INPUT PARSER ==============");
        //Call parser
        List<Picture> pictures = inputParser(scanner);
        log.info("============== END INPUT PARSER ==============");

        log.info("============== START SOLUTION ==============");

        List<Picture> picturesH  = pictures.stream().filter(p -> p.getOrientation()==0).
                collect(Collectors.toList());
        List<Picture> picturesV  = pictures.stream().filter(p -> p.getOrientation()==1).
                collect(Collectors.toList());

        List<Picture> picturesHOrdered = picturesH.stream().sorted(Comparator.comparing(p -> p.getTags().size())).
                collect(Collectors.toList());
        Collections.reverse(picturesHOrdered);

        List<Picture> picturesVOrdered = picturesV.stream().sorted(Comparator.comparing(p -> p.getTags().size())).
                collect(Collectors.toList());
        Collections.reverse(picturesVOrdered);

        if( picturesVOrdered.size() % 2 == 1 ){
            picturesVOrdered.remove(0);
        }

        List<Slide> backlogSlide = new ArrayList<>();
        for (Picture p : picturesHOrdered){
            Slide slide = new Slide();
            slide.addPicture(p.getId(), p.getTags());
            backlogSlide.add(slide);
        }
        Slide slide = new Slide();
        for (int i = 0; i< picturesVOrdered.size(); i++){
            slide.addPicture(picturesVOrdered.get(i).getId(), picturesVOrdered.get(i).getTags());
            if(i%2 == 1) {
                backlogSlide.add(slide);
                slide = new Slide();
            }
        }

        //Reorderer backlog
        this.slideShow = new SlideShow();
        int indexx = 0;
        if(backlogSlide.size()<3000){
            Slide workingSlide = backlogSlide.get(0);
            algo1(workingSlide,0, backlogSlide,this.slideShow);
        }else{

            for (int i = 0; i< backlogSlide.size(); i++){
                if(i%3000 == 0) {
                    int sublistmax = 2999 + i;
                    if(i+2999 > backlogSlide.size()){
                        sublistmax = backlogSlide.size();
                    }
                    List<Slide> tempBacklogslide = backlogSlide.subList(i,sublistmax);
                    Slide workingSlide = tempBacklogslide.get(0);
                    algo1(workingSlide,0, tempBacklogslide,this.slideShow);
                }
            }
        }


    }

    private void algo1(Slide workingSlide, int index,  List<Slide> backlogSlide, SlideShow ss){
        backlogSlide.remove(index);
        ss.getSlides().add(workingSlide);
        boolean findWorkingSlide = false;
        for(int i =0; i< backlogSlide.size(); i++){
            if(backlogSlide.get(i).getTags().stream()
                    .filter(workingSlide.getTags()::contains)
                    .collect(Collectors.toList()).size() >= 3){
                workingSlide = backlogSlide.get(i);
                index = i;
                findWorkingSlide= true;
                break;
            }
        }
        if(findWorkingSlide){
            algo1(workingSlide, index, backlogSlide, ss);
        }else if (backlogSlide.size()>0){
                algo1(backlogSlide.get(0), 0, backlogSlide, ss);
        }
    }

    private List<Picture> inputParser(final Scanner scanner){
        int nbPicture = Integer.parseInt(scanner.nextLine().split(" ")[0]);
        List<Picture> pictures = new ArrayList<Picture>();
        int id = 0;
        while (scanner.hasNext()) {
            final String[] picture = scanner.nextLine().split(" ");
            String orientation = picture[0];
            int orient = 0;
            if(orientation.equals("V")){
                orient = 1;
            }
            List<String> tags = Arrays.stream(picture).collect(Collectors.toList());
            tags.remove(0);
            tags.remove(1);
            pictures.add(new Picture(id, orient, tags ));
            id++;
        }
        return pictures;
    }

    public List<String> getOutput() {
        List<String> output = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        sb.append(this.slideShow.getSlides().size());
        output.add(sb.toString());

        this.slideShow.getSlides().stream().forEach(slide -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(slide.getPictures().get(0));
            if (slide.getPictures().size() > 1) {
                stringBuilder.append(' ');
                stringBuilder.append(slide.getPictures().get(1));
            }
            output.add(stringBuilder.toString());
        });

        return output;
    }
}
