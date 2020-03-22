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

package org.gate.gui.graph.elements.asseration;

import org.gate.common.util.GateException;
import org.gate.gui.details.results.elements.graph.AssertionResult;
import org.gate.gui.details.results.elements.graph.ElementResult;
import org.gate.gui.graph.elements.asseration.gui.TextAssertGui;

import org.gate.runtime.GateContextService;


public class ResponseAssert extends TextAssert {

    @Override
    String getInput() throws GateException {
        return GateContextService.getContext().getPreviousResult().getResponseAsString();
    }
    @Override
    public String getGUI(){
	    return TextAssertGui.class.getName();
    }

}