package com.kwic.kwic.services;

import com.kwic.kwic.entities.Url;
import com.kwic.kwic.pf.context.Context;
import com.kwic.kwic.pf.filters.AlphabetizerFilter;
import com.kwic.kwic.pf.filters.CircularFilter;
import com.kwic.kwic.pf.filters.NoiseWordFilter;
import com.kwic.kwic.pf.pipeline.SimplePipeline;
import com.kwic.kwic.repo.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    public void insert(Url url){
        urlRepository.save(url);
    }
    public List<Url> search(String search){
        Context context = new Context();
        List<Url> results = new ArrayList<Url>();
        SimplePipeline pipeline = new SimplePipeline();
        CircularFilter circularFilter = new CircularFilter();
        AlphabetizerFilter alphabetizerFilter = new AlphabetizerFilter();
        NoiseWordFilter noiseWordFilter = new NoiseWordFilter();
        //check this!!
        pipeline.linkedFilters.add(circularFilter);
        pipeline.linkedFilters.add(alphabetizerFilter);
        pipeline.linkedFilters.add(noiseWordFilter);

        List<Url> inMemory = urlRepository.findAll();
        for(Url url: inMemory){
            context.clearParameterByKey("key");
            context.putParameter("key", url.getDescription());
           List<String> pipelineResults = new ArrayList<String>();
           pipelineResults = (List<String>) pipeline.execute(context);

            ArrayList<String> shifted = context.getParameter("key");
            for(String match: shifted){
                if(match.contains(search)){
                    results.add(url);
                    break;
                }
                break;
            }
        }
        return results;
    }
}
