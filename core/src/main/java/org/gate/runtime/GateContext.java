/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.gate.runtime;

import org.gate.common.util.CopyUtils;
import org.gate.gui.details.results.collector.ResultCollector;
import org.gate.gui.details.results.elements.graph.SamplerResult;

import java.util.HashMap;

/**
 * Holds context for a thread.
 * Generated by GateContextService.
 * 
 * The class is not thread-safe - it is only intended for use within a single thread.
 */
public class GateContext {

	private GateVariables variables;
    private HashMap<String, Object> configs = new HashMap<>();
    // for models running in same thread. currently for actions in test case
    private HashMap<String, Object> graphElementContext = new HashMap<>(5);
    private SamplerResult previousResult;
    private ResultCollector resultCollector;

    private Thread thread;
    private String testSuitesName;
    private volatile boolean isModelShutdown;

    public GateContext() {
        clear();
        isModelShutdown = false;
        thread = null;
        testSuitesName = null;
        variables = new GateVariables();

    }

    public void setTestSuitesName(String testSuitesName){
        this.testSuitesName = testSuitesName;
    }

    public String getTestSuitesName(){
        return testSuitesName;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread){
        this.thread = thread;
    }

    public boolean isModelShutdown() {
        return isModelShutdown;
    }

    public void modelShutdown() {
        isModelShutdown = true;
        clear();
    }

    public void clear() {
        variables = null;
        configs.clear();
        graphElementContext.clear();
        previousResult = null;
        resultCollector = null;
    }
    // deepCopy variables and configs from a context
    public void copy(GateContext context){
        variables = CopyUtils.deepCopy(context.getVariables()).get();
        configs = CopyUtils.deepCopy(context.getConfigs()).get();
        testSuitesName = context.getTestSuitesName();
    }

    public void setResultCollector(ResultCollector resultCollector){
        this.resultCollector = resultCollector;
    }

    public ResultCollector getResultCollector(){
        return resultCollector;
    }
    /**
     * Gives access to the Gate variables for the current thread.
     * @return a pointer to the Gate variables.
     */
    public GateVariables getVariables() {
        return variables;
    }

    public HashMap<String, Object> getConfigs(){
        return configs;
    }

    public HashMap<String, Object> getGraphElementContext(){
        return graphElementContext;
    }

    public void setVariables(GateVariables vars) {
        this.variables = vars;
    }

    public SamplerResult getPreviousResult() {
        return previousResult;
    }

    public void setPreviousResult(SamplerResult result) {
        this.previousResult = result;
    }

    /*
    *  Not sure this will be use or not. Model executor hold it current and latest result.
    *  previous result just use for expose the result to other components
    * */
//    private ElementResult lastResult = null;
//    public ElementResult getLastResult() {
//        return lastResult ;
//    }
//    public void setLastResult(ElementResult result) {
//        lastResult = result;
//    }



}