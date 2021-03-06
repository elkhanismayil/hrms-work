package com.company.hrms.controllers;

import com.company.hrms.business.abstracts.JobTitleService;
import com.company.hrms.core.utilities.results.DataResult;
import com.company.hrms.core.utilities.results.Result;
import com.company.hrms.core.utilities.results.SuccessDataResult;
import com.company.hrms.entities.concretes.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/jobs")
public class JobTitleController {

    private JobTitleService service;

    @Autowired
    public JobTitleController(JobTitleService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get-all")
    public List<JobTitle> getAll() {
        return this.service.getAll();
    }

    @RequestMapping(value = "/get_all_paginated", method = RequestMethod.GET)
    public DataResult<List<JobTitle>> getAllPaginated(@RequestParam int pageNo, @RequestParam int pageSize) {
        return this.service.getAllPaginated(pageNo, pageSize);
    }

    @RequestMapping(value = "/get_all_sorted_asc", method = RequestMethod.GET)
    public DataResult<List<JobTitle>> getAllSorted() {
        return this.service.getAllSorted();
    }

    @RequestMapping(value = "/get_jobs_active", method = RequestMethod.GET)
    public DataResult<List<JobTitle>> getByStatusIsTrue(){
        List<JobTitle> byStatusTrue = service.findByStatusTrue();
        return new SuccessDataResult<>(byStatusTrue,"Success!");
    }

    @RequestMapping(value = "/get_jobs_inactive", method = RequestMethod.GET)
    public DataResult<List<JobTitle>> getByStatusIsFalse(){
        List<JobTitle> byStatusFalse = service.findByStatusFalse();
        return new SuccessDataResult<>(byStatusFalse,"Success!");
    }

    @RequestMapping(value = "/get_by_id", method = RequestMethod.GET)
    public DataResult<JobTitle> getById(@RequestParam int id) {
        return this.service.getById(id);
    }

    @RequestMapping(value = "/get_job_desc", method = RequestMethod.GET)
    public DataResult<JobTitle> getByJobDescription(@RequestParam String jobDescription) {
        return this.service.getByJobDescription(jobDescription);
    }

    @RequestMapping(value = "/get_job_desc_contains", method = RequestMethod.GET)
    public DataResult<List<JobTitle>> findJobTitlesByJobDescriptionContains(@RequestParam String jobDescription) {
        return this.service.findJobTitlesByJobDescriptionContains(jobDescription);
    }

    @RequestMapping(value = "/get_job_desc_starts_with", method = RequestMethod.GET)
    public DataResult<List<JobTitle>> findJobTitlesByJobDescriptionStartsWith(@RequestParam String jobDescription) {
        return this.service.findJobTitlesByJobDescriptionStartsWith(jobDescription);
    }

    @RequestMapping(value = "/get_job_desc_ends_with", method = RequestMethod.GET)
    public DataResult<List<JobTitle>> findJobTitlesByJobDescriptionEndsWith(@RequestParam String jobDescription) {
        return this.service.findJobTitlesByJobDescriptionEndsWith(jobDescription);
    }

    @RequestMapping(value = "/add_desc", method = RequestMethod.POST)
    public Result add(@RequestBody JobTitle title) {
        return this.service.add(title);
    }

    @RequestMapping(value = "/update_desc", method = RequestMethod.POST)
    public DataResult<JobTitle> updateFieldById(@RequestParam String jobDescription, @RequestParam int id) {
        return service.updateFieldById(jobDescription, id);
    }

    @RequestMapping(value = "/delete_by_id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteById(@PathVariable int id) {
        var isRemoved = this.service.deleteById(id);
        if (isRemoved.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
