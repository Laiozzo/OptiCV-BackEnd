package com.generation.opticvbeckend.controllers.helpers;

import com.generation.opticvbeckend.automations.RequestData;
import com.generation.opticvbeckend.configuration.LlamaApiClient;
import com.generation.opticvbeckend.model.entities.UploadedFile;
import com.generation.opticvbeckend.model.entities.User;
import com.generation.opticvbeckend.model.parse.CVParserIText;
import com.generation.opticvbeckend.model.repositories.UploadedFileRepository;
import com.generation.opticvbeckend.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CvHelpers
{
    @Autowired
    CVParserIText parser;
    @Autowired
    UploadedFileRepository uploadedFileRepository;
    @Autowired
    AnswerService answerService;

    public List<String> processFile(Long id, String jobDescription){

        Optional<UploadedFile> o = uploadedFileRepository.findById(id);
        if(o.isEmpty())
            return null;

        return answerService.processQuestions(jobDescription, o.get().getParsedContent());
    }

    public List<String> processLastFileCurrentUser(String jobDescription)
    {
        User u = RequestData.getUser();
        Long id = u.getUploadedFiles().getLast().getId();
        return processFile(id, jobDescription);

    }

}
