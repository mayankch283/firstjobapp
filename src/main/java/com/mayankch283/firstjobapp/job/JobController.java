package com.mayankch283.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findJob(@PathVariable long id){
        Job job = jobService.findJob(id);
        if(job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity("Job created successfully.", HttpStatus.CREATED);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@RequestBody Job job, @PathVariable long id){
        job.setId(id);
        if(jobService.updateJob(job)){
            return new ResponseEntity("Job updated successfully.", HttpStatus.OK);
        }
        return new ResponseEntity("No Job found.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable long id){
        Job job = jobService.findJob(id);
        if(job!=null){
            jobService.deleteJob(job);
            return new ResponseEntity("Job deleted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity("Job not found.", HttpStatus.NOT_FOUND);
    }
}
