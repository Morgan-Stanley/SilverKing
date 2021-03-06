package com.ms.silverking.cloud.dht;

import static com.ms.silverking.cloud.dht.NamespaceVersionMode.CLIENT_SPECIFIED;
import static com.ms.silverking.cloud.dht.NamespaceVersionMode.SEQUENTIAL;
import static com.ms.silverking.cloud.dht.NamespaceVersionMode.SINGLE_VERSION;
import static com.ms.silverking.cloud.dht.NamespaceVersionMode.SYSTEM_TIME_MILLIS;
import static com.ms.silverking.cloud.dht.NamespaceVersionMode.SYSTEM_TIME_NANOS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ms.silverking.cloud.dht.client.gen.OmitGeneration;

@OmitGeneration
public class NamespaceVersionModeTest {

  @Test
  public void testIsValidVersion() {
    NamespaceVersionMode[] testCases = { SINGLE_VERSION, CLIENT_SPECIFIED, SEQUENTIAL, SYSTEM_TIME_MILLIS,
        SYSTEM_TIME_NANOS, };

    for (NamespaceVersionMode testCase : testCases)
      assertTrue(testCase.validVersion(1_000_000_000));
  }

  @Test
  public void testIsSystemSpecified() {
    Object[][] testCases = { { SINGLE_VERSION, false }, { CLIENT_SPECIFIED, false }, { SEQUENTIAL, true },
        { SYSTEM_TIME_MILLIS, true }, { SYSTEM_TIME_NANOS, true }, };

    for (Object[] testCase : testCases) {
      NamespaceVersionMode mode = (NamespaceVersionMode) testCase[0];
      boolean expected = (boolean) testCase[1];

      assertEquals(expected, mode.isSystemSpecified());
    }
  }

}
