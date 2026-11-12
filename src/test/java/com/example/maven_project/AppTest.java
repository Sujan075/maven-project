
package com.example.maven_project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

public class AppTest {

    // 1. Test valid mobile number
    @Test
    void testValidMobileNumber() {
        assertTrue(App.isValidMobileNumber("9876543210"));
    }

    // 2. Test invalid mobile number formats
    @Test
    void testInvalidMobileNumber() {
        assertFalse(App.isValidMobileNumber("123456789"));
        assertFalse(App.isValidMobileNumber("12345678901"));
        assertFalse(App.isValidMobileNumber("5876543210"));
        assertFalse(App.isValidMobileNumber("abcdefghij"));
        assertFalse(App.isValidMobileNumber(null));
    }

    // 3. Test displaying available recharge plans
    @Test
    void testDisplayRechargePlans() {
        List<App.RechargePlan> plans = App.getRechargePlans();

        assertEquals(3, plans.size());
        assertEquals(199, plans.get(0).getAmount());
        assertEquals(299, plans.get(1).getAmount());
        assertEquals(399, plans.get(2).getAmount());
    }

    // 4. Test selecting an available plan
    @Test
    void testSelectRechargePlan() {
        App.RechargePlan plan = App.getPlanById(2);

        assertNotNull(plan);
        assertEquals(2, plan.getPlanId());
        assertEquals(299, plan.getAmount());
    }

    // 5. Test invalid plan selection
    @Test
    void testInvalidPlanSelection() {
        assertNull(App.getPlanById(5));
    }

    // 6. Test correct recharge amount
    @Test
    void testRechargeAmount() {
        App.RechargePlan plan = App.getPlanById(1);

        assertEquals(199, App.calculateRechargeAmount(plan));
    }

    // 7. Test invalid recharge amount
    @Test
    void testInvalidRechargeAmount() {
        assertFalse(App.isValidRechargeAmount(0));
        assertFalse(App.isValidRechargeAmount(-100));
    }

    // 8. Test recharge with invalid plan
    @Test
    void testRechargeWithInvalidPlan() {
        assertThrows(IllegalArgumentException.class, () -> {
            App.calculateRechargeAmount(null);
        });
    }

}
