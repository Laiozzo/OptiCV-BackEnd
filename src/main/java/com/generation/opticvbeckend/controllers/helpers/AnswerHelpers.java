package com.generation.opticvbeckend.controllers.helpers;

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
public class AnswerHelpers
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
        String filePath = o.get().getFilePath();
        String cv = parser.pdfToString(filePath);

        return answerService.processQuestions(jobDescription, cv);
    }

    public List<String> processLastFileCurrentUser(String jobDescription)
    {
        //User u = RequestData.getUser().utente;
        User u = new User();
        Long id = u.getUploadedFiles().getLast().getId();
        return processFile(id, jobDescription);

    }

}
