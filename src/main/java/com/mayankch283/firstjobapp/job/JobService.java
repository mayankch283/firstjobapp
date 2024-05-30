package com.mayankch283.firstjobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job findJob(long id);
    void deleteJob(Job job);
    boolean updateJob(Job job, Long id);
}
