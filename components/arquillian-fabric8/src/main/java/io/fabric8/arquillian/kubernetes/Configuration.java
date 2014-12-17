/*
 * Copyright 2005-2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.fabric8.arquillian.kubernetes;

import java.net.URL;
import java.util.Map;

public class Configuration {

    private String masterUrl;
    private URL configUrl;

    public String getMasterUrl() {
        return masterUrl;
    }

    public URL getConfigUrl() {
        return configUrl;
    }


    public static Configuration fromMap(Map<String, String> map) {
        Configuration configuration = new Configuration();
        try {
            if (map.containsKey(Constants.MASTER_URL)) {
                configuration.masterUrl = map.get(Constants.MASTER_URL);
            }
            if (map.containsKey(Constants.CONFIG_URL)) {
                configuration.configUrl = new URL(map.get(Constants.CONFIG_URL));
            }
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        return configuration;
    }
}

