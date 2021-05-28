package controllers;

import io.javalin.http.Handler;
import com.google.gson.Gson;
import models.Grade;
import models.Note;
import models.Assessment;
import models.Type;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import services.AssessmentService;

import java.util.List;


public class AssessmentController {
    private static Logger aclogger = LogManager.getLogger(AssessmentController.class);

    private AssessmentService as;
    private Gson gson = new Gson();


    public AssessmentController(AssessmentService as) {
        //Setting Service
        this.as = as;
    }


    public Handler getAssessmentForTrainee = (context) -> {
        try {
            aclogger.info("attempting to get assessments for a trainee");
            int id = Integer.parseInt(context.pathParam("id"));
            String weekId = context.pathParam("weekid");
            aclogger.info("attempting to get assessments");
            Assessment assessment = as.getAssesmentForTrainee(id, weekId);
            context.contentType("application/json");
            context.result(gson.toJson(assessment));
        } catch (Exception e) {
            aclogger.info(e);
        }

    };


    public Handler getNotesForTrainee = (context) -> {
        try {
            aclogger.info("attempting to get notes params");
            int id = Integer.parseInt(context.pathParam("id"));
            String weekId = context.pathParam("weekid");
            aclogger.info("attempting to get notes");
            List<Note> notes = as.getNotesForTrainee(id, weekId);
            context.contentType("application/json");
            context.result(gson.toJson(notes));
        } catch (Exception e) {
            aclogger.info(e);
        }
    };

    public Handler createAssessment = (context) -> {
        try {
            aclogger.info("attempting to create an assessment");
            Assessment assessment = gson.fromJson(context.body(), Assessment.class);
            Assessment updatedAssessment = as.createAssessment(assessment);
            context.contentType("application/json");
            aclogger.info("attempting to return updated assessment");
            context.result(gson.toJson(updatedAssessment));
        } catch (Exception e) {
            aclogger.info(e);
        }
    };

    public Handler insertGrade = (context) -> {
        try {
            aclogger.info("attempting to update the grade on an assessment");
            Grade grade = gson.fromJson(context.body(), Grade.class);
            Grade insertedGrade = as.insertGrade(grade);
            context.contentType("application/json");
            aclogger.info("attempting to return inserted  grade");
            context.result(gson.toJson(insertedGrade));
        } catch (Exception e) {
            aclogger.info(e);
        }
    };

    public Handler updateWeightForAssessment = (context) -> {
        aclogger.info("attempting to update the weight on an assessment");
        try {
            aclogger.info("attempting to update the grade on an assessment");
            int weight = Integer.parseInt(context.pathParam("weight"));
            int updatedWeight = as.updateWeightForAssessment(weight);
            context.contentType("application/json");
            aclogger.info("attempting to return updatedWeight");
            context.result(gson.toJson(updatedWeight));
        } catch (Exception e) {
            aclogger.info(e);
        }
    };
    public Handler createType = (context) -> {
        try {
            aclogger.info("attempting to create a Type for assessments");
            Type type = gson.fromJson(context.body(), Type.class);
            Type updatedType = as.createAssessmentType(type);
            context.contentType("application/json");
            aclogger.info("attempting to return updated type");
            context.result(gson.toJson(updatedType));
        } catch (Exception e) {
            aclogger.info(e);
        }
    };
    public Handler updateTypeForAssessment = (context) -> {
        aclogger.info("attempting to update type for assessment");
        try {
            int typeId = Integer.parseInt(context.pathParam("typeId"));
            int updatedTypeId = as.updateTypeForAssessment(typeId);
            context.contentType("application/json");
            aclogger.info("attempting to return updated type for assessment");
            context.result(gson.toJson(updatedTypeId));
        } catch (Exception e) {
            aclogger.info(e);
        }
    };


}
