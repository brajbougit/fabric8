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

import io.fabric8.arquillian.kubernetes.event.Start;
import io.fabric8.arquillian.kubernetes.event.Stop;
import io.fabric8.kubernetes.api.Controller;
import io.fabric8.kubernetes.api.Entity;
import io.fabric8.kubernetes.api.KubernetesClient;
import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.PodSchema;
import io.fabric8.kubernetes.api.model.ReplicationControllerSchema;
import io.fabric8.kubernetes.api.model.ServiceSchema;
import org.jboss.arquillian.core.api.annotation.Observes;

import static io.fabric8.arquillian.utils.Util.cleanupSession;
import static io.fabric8.arquillian.utils.Util.readAsString;
import static io.fabric8.kubernetes.api.KubernetesHelper.getEntities;
import static io.fabric8.kubernetes.api.KubernetesHelper.loadJson;

public class SessionListener {

    public void start(@Observes Start event, Controller controller, Configuration configuration) {
        try {
            Object dto = loadJson(readAsString(configuration.getConfigUrl()));
            if (dto instanceof Config) {
                for (Entity entity : getEntities((Config) dto)) {
                    if (entity instanceof PodSchema) {
                        PodSchema pod = (PodSchema) entity;
                        pod.getLabels().put(Constants.ARQ_KEY, event.getSession().getId());
                    } else if (entity instanceof ServiceSchema) {
                        ServiceSchema service = (ServiceSchema) entity;
                        service.getLabels().put(Constants.ARQ_KEY, event.getSession().getId());
                    } else if (entity instanceof ReplicationControllerSchema) {
                        ReplicationControllerSchema replicationController = (ReplicationControllerSchema) entity;
                        replicationController.getDesiredState().getPodTemplate().getLabels().put(Constants.ARQ_KEY, event.getSession().getId());
                        replicationController.getLabels().put(Constants.ARQ_KEY, event.getSession().getId());
                        controller.applyReplicationController(replicationController, event.getSession().getId());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop(@Observes Stop event, KubernetesClient client) throws Exception {
        cleanupSession(client, event.getSession());
    }
}
