package com.example.jc321013.test;

// This class contains a list of questions
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    // declare list of Question objects
    List<Question> list = new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;

    // method returns number of questions in list
    public int getLength() {
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num - 1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }


    public void initQuestions(Context context) {
        myDataBaseHelper = new MyDataBaseHelper(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialQuestion(new Question("Bushfires are only caused by humans?",
                    new String[]{"True", "False", " "}, "False"));
            myDataBaseHelper.addInitialQuestion(new Question("What is a flood?",
                    new String[]{"Excess water", "Heavy Rainfall", "An overflow of water that submerges land"}, "An overflow of water that submerges land"));
            myDataBaseHelper.addInitialQuestion(new Question("Tropical Cyclones form over warm waters?",
                    new String[]{"True", "False", " "}, "True"));
            myDataBaseHelper.addInitialQuestion(new Question("To cause damage an earthquake needs to exceed what magnitude?",
                    new String[]{"9", "6", "7"}, "7"));
            myDataBaseHelper.addInitialQuestion(new Question("What isn't a requirement for severe storms to develop?",
                    new String[]{"Moist/Humid air", "Rainfall", "An area of low pressure"}, "Rainfall"));
            myDataBaseHelper.addInitialQuestion(new Question("What is a Tsunami?",
                    new String[]{"A Massive wave", "Big Surf", "A large wave usually formed by undersea earthquakes and landslides"}, "A large wave usually formed by undersea earthquakes and landslides"));

            myDataBaseHelper.addInitialQuestion(new Question("Is an Earthquake caused by sudden movements of tectonic plates?",
                    new String[]{"yes", "no", " "}, "yes"));
            myDataBaseHelper.addInitialQuestion(new Question("Which of the following is not a natural hazard?",
                    new String[]{"Sunny Days", "Earthquakes", "Cyclones"}, "Sunny Days"));

            myDataBaseHelper.addInitialQuestion(new Question("Which of the following is INCORRECT about Volcanoes?",
                    new String[]{"A Volcano is a mountain containing a crater filled with lava", "A Volcano can only irrupt above sea level", "A Volcano produces smoke, magma and gas"}, "A Volcano can only irrupt above sea level"));
            myDataBaseHelper.addInitialQuestion(new Question("How is a tornado formed?",
                    new String[]{"Two air masses that create instability in the atmosphere", "Strong winds", "Cluster of thunderstorms and a warm body of water"}, "Two air masses that create instability in the atmosphere"));
            myDataBaseHelper.addInitialQuestion(new Question("An Avalanche is a rapid flow of snow down a sloping surface?",
                    new String[]{"True", "False", " "}, "True"));
            myDataBaseHelper.addInitialQuestion(new Question("Landslides are caused by three factors: snow cover, weak layer of snow cover & a trigger ",
                    new String[]{"True", "False", " "}, "False"));


            list = myDataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }
}

