/*
 * Copyright 2017-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.net.pi.runtime;

import com.google.common.testing.EqualsTester;
import org.junit.Test;
import org.onosproject.net.pi.model.PiActionId;
import org.onosproject.net.pi.model.PiActionParamId;
import org.onosproject.net.pi.model.PiActionProfileId;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.onlab.junit.ImmutableClassChecker.assertThatClassIsImmutable;
import static org.onlab.util.ImmutableByteSequence.copyFrom;
import static org.onosproject.net.pi.runtime.PiConstantsTest.DST_ADDR;
import static org.onosproject.net.pi.runtime.PiConstantsTest.MOD_NW_DST;

/**
 * Unit tests for PiActionGroupMember class.
 */
public class PiActionGroupMemberTest {

    private final PiActionProfileId actionProfileId1 = PiActionProfileId.of("foo");
    private final PiActionProfileId actionProfileId2 = PiActionProfileId.of("bar");
    private final PiActionGroupMemberId piActionGroupMemberId = PiActionGroupMemberId.of(10);
    private final PiAction piAction = PiAction.builder().withId(PiActionId.of(MOD_NW_DST))
            .withParameter(new PiActionParam(PiActionParamId.of(DST_ADDR), copyFrom(0x0a010101)))
            .build();

    private final PiActionGroupMember piActionGroupMember1 = PiActionGroupMember.builder()
            .forActionProfile(actionProfileId1)
            .withId(piActionGroupMemberId)
            .withAction(piAction)
            .withWeight(10)
            .build();
    private final PiActionGroupMember sameAsPiActionGroupMember1 = PiActionGroupMember.builder()
            .forActionProfile(actionProfileId1)
            .withId(piActionGroupMemberId)
            .withAction(piAction)
            .withWeight(10)
            .build();
    private final PiActionGroupMember piActionGroupMember2 = PiActionGroupMember.builder()
            .forActionProfile(actionProfileId1)
            .withId(piActionGroupMemberId)
            .withAction(piAction)
            .withWeight(20)
            .build();
    private final PiActionGroupMember piActionGroupMember1ForOtherProfile = PiActionGroupMember.builder()
            .forActionProfile(actionProfileId2)
            .withId(piActionGroupMemberId)
            .withAction(piAction)
            .withWeight(10)
            .build();

    /**
     * Checks that the PiActionGroupMember class is immutable.
     */
    @Test
    public void testImmutability() {

        assertThatClassIsImmutable(PiActionGroupMember.class);
    }

    /**
     * Checks the operation of equals(), hashCode() and toString() methods.
     */
    @Test
    public void testEquals() {

        new EqualsTester()
                .addEqualityGroup(piActionGroupMember1, sameAsPiActionGroupMember1)
                .addEqualityGroup(piActionGroupMember2)
                .addEqualityGroup(piActionGroupMember1ForOtherProfile)
                .testEquals();
    }

    /**
     * Checks the methods of PiActionGroupMember.
     */
    @Test
    public void testMethods() {

        assertThat(piActionGroupMember1, is(notNullValue()));
        assertThat(piActionGroupMember1.weight(), is(10));
        assertThat(piActionGroupMember1.id(), is(piActionGroupMemberId));
        assertThat(piActionGroupMember1.action(), is(piAction));
    }
}
