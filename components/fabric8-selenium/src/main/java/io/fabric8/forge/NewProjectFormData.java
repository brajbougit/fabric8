/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.forge;

/**
 * Represents the data to input into the Create Project wizard page
 */
public class NewProjectFormData {
    private String named = "cheese";
    private String type = "From Archetype Catalog";
    private String archetypeFilter;
    private String jenkinsFileFilter;

    public NewProjectFormData(String named, String archetypeFilter, String jenkinsFileFilter) {
        this.named = named;
        this.archetypeFilter = archetypeFilter;
        this.jenkinsFileFilter = jenkinsFileFilter;
    }


    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArchetypeFilter() {
        return archetypeFilter;
    }

    public void setArchetypeFilter(String archetypeFilter) {
        this.archetypeFilter = archetypeFilter;
    }

    public String getJenkinsFileFilter() {
        return jenkinsFileFilter;
    }

    public void setJenkinsFileFilter(String jenkinsFileFilter) {
        this.jenkinsFileFilter = jenkinsFileFilter;
    }
}
