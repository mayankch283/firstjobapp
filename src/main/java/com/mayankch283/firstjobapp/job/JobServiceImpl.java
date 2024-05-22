package com.mayankch283.firstjobapp.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    private long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job findJob(long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteJob(Job job) {
//        Iterator iterator = jobs.iterator();
//        while(iterator.hasNext()){
//            Job job = (Job) iterator.next();
//            if(job.getId() == id){
//                iterator.remove();
//                return;
//            }
//        }
//
//        This approach can lead to ConcurrentModificationException because
//        the collection is being modified while it is being iterated over.
//        for(Job job : jobs){
//            if(job.getId() == id){
//                jobs.remove(job);
//                return;
//            }
//        }
        jobRepository.delete(job);
    }

    @Override
    public boolean updateJob(Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(updatedJob.getId());
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
