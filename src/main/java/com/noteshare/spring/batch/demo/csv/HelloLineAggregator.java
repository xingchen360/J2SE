package com.noteshare.spring.batch.demo.csv;

import org.springframework.batch.item.file.transform.LineAggregator;

/**
 * 
 * @author itnoteshare
 *
 */
public class HelloLineAggregator implements LineAggregator<DeviceCommand> {

    @Override
    public String aggregate(DeviceCommand deviceCommand) {
        StringBuffer sb = new StringBuffer();
        sb.append(deviceCommand.getId());
        sb.append(",");
        sb.append(deviceCommand.getStatus());
        return sb.toString();
    }
}
