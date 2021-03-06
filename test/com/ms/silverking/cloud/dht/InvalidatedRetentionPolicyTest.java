package com.ms.silverking.cloud.dht;

import static com.ms.silverking.cloud.dht.TestUtil.getImplementationType;
import static com.ms.silverking.cloud.dht.ValueRetentionPolicy.ImplementationType.SingleReverseSegmentWalk;
import static com.ms.silverking.testing.AssertFunction.checkHashCodeEquals;
import static com.ms.silverking.testing.AssertFunction.checkHashCodeNotEquals;
import static com.ms.silverking.testing.AssertFunction.test_FirstEqualsSecond_FirstNotEqualsThird;
import static com.ms.silverking.testing.AssertFunction.test_Getters;
import static com.ms.silverking.testing.AssertFunction.test_NotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ms.silverking.cloud.dht.client.gen.OmitGeneration;

@OmitGeneration
public class InvalidatedRetentionPolicyTest {

  private static final int irisCopy = 0;
  private static final int irisDiff = 1;

  private static final InvalidatedRetentionPolicy defaultPolicy = InvalidatedRetentionPolicy.template;
  private static final InvalidatedRetentionPolicy defaultPolicyCopy = new InvalidatedRetentionPolicy(irisCopy);
  private static final InvalidatedRetentionPolicy defaultPolicyDiff = new InvalidatedRetentionPolicy(irisDiff);

  @Test
  public void testGetters() {
    Object[][] testCases = { { SingleReverseSegmentWalk, getImplementationType(defaultPolicy) },
        //            {new InvalidatedRetentionState(), getInitialState(defaultPolicy)},    // FIXME:bph: currently
        //             no equals on InvalidatedRetentionState so this will assert !equal
    };

    test_Getters(testCases);
  }

  @Test
  public void testRetains() {
    long irisCopyLong = (long) irisCopy;
    Object[][] testCases = {
        { defaultPolicy, null, irisCopyLong, irisCopyLong, false, new InvalidatedRetentionState(), 0L, true },
        { defaultPolicy, null, irisCopyLong, irisCopyLong, true, new InvalidatedRetentionState(), 0L, false },
        { defaultPolicy, null, irisCopyLong, irisCopyLong + 1, true, new InvalidatedRetentionState(), 0L, true }, };

    TestUtil.checkRetains(testCases);
  }

  @Test
  public void testHashCode() {
    checkHashCodeEquals(defaultPolicy, defaultPolicy);
    checkHashCodeEquals(defaultPolicy, defaultPolicyCopy);
    checkHashCodeNotEquals(defaultPolicy, defaultPolicyDiff);
  }

  @Test
  public void testEqualsObject() {
    InvalidatedRetentionPolicy[][] testCases = { { defaultPolicy, defaultPolicy, defaultPolicyDiff },
        { defaultPolicyCopy, defaultPolicy, defaultPolicyDiff },
        { defaultPolicyDiff, defaultPolicyDiff, defaultPolicy }, };
    test_FirstEqualsSecond_FirstNotEqualsThird(testCases);

    test_NotEquals(new Object[][] { { defaultPolicy, PermanentRetentionPolicy.template },
        { defaultPolicy, TimeAndVersionRetentionPolicy.template }, });
  }

  @Test
  public void testToStringAndParse() {
    InvalidatedRetentionPolicy[] testCases = { defaultPolicy, defaultPolicyCopy, defaultPolicyDiff, };

    for (InvalidatedRetentionPolicy testCase : testCases)
      checkStringAndParse(testCase);
  }

  private void checkStringAndParse(InvalidatedRetentionPolicy policy) {
    assertEquals(policy, InvalidatedRetentionPolicy.parse(policy.toString()));
  }
}
