package org.word.service;

import java.util.List;
import java.util.Map;

/**
 * @author tuonbed
 * @date 2019/10/29  9:26
 */
public interface WordService2 {
    List<Map<String, Map>> apiMap(String[] jsonUrls);
}
