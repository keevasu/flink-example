package com.flinkexample.flinkapplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class FlinkApplicationRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlinkApplicationRestController.class);

    @Autowired
    private PostionsPublisher postionsPublisher;

    @RequestMapping(value = "/kafka_to_flink", method = RequestMethod.POST)
    public String resultset1(@RequestParam("Ref_id") int ref_id)
            throws Exception
    {
        String s= postionsPublisher.producer(ref_id);
        LOGGER.debug(s);
        return "All results pushed to kafka for -> "+ref_id;
    }


}