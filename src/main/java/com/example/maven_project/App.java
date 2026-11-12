
package com.example.maven_project;

import java.util.*;

public class App {

    // Predefined recharge plans
    static class RechargePlan {
        private int planId;
        private int amount;
        private String validity;
        private String benefits;

        RechargePlan(int planId, int amount, String validity, String benefits) {
            this.planId = planId;
            this.amount = amount;
            this.validity = validity;
            this.benefits = benefits;
        }

        public int getPlanId() {
            return planId;
        }

        public int getAmount() {
            return amount;
        }

        public String getValidity() {
            return validity;
        }

        public String getBenefits() {
            return benefits;
        }
    }

    // Available recharge plans
    private static final List<RechargePlan> plans = List.of(
        new RechargePlan(1, 199, "28 Days", "1 GB/Day + Unlimited Calls"),
        new RechargePlan(2, 299, "28 Days", "1.5 GB/Day + Unlimited Calls"),
        new RechargePlan(3, 399, "56 Days", "2 GB/Day + Unlimited Calls")
    );

    // Validate mobile number
    public static boolean isValidMobileNumber(String number) {
        return number != null && number.matches("[6-9][0-9]{9}");
    }

    // Display available recharge plans
    public static List<RechargePlan> getRechargePlans() {
        return plans;
    }

    // Find plan using plan ID
    public static RechargePlan getPlanById(int id) {
        for (RechargePlan plan : plans) {
            if (plan.getPlanId() == id) {
                return plan;
            }
        }
        return null;
    }

    // Validate recharge amount
    public static boolean isValidRechargeAmount(int amount) {
        return amount > 0;
    }

    // Calculate recharge amount
    public static int calculateRechargeAmount(RechargePlan plan) {
        if (plan == null || !isValidRechargeAmount(plan.getAmount())) {
            throw new IllegalArgumentException("Invalid recharge plan or amount");
        }
        return plan.getAmount();
    }

    // Main application
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== MOBILE RECHARGE APPLICATION =====");

        // Accept mobile number
        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine();

        // Validate mobile number
        if (!isValidMobileNumber(mobile)) {
            System.out.println("Invalid Mobile Number!");
            sc.close();
            return;
        }

        System.out.println("\nMobile Number Verified Successfully!");

        // Display recharge plans
        System.out.println("\nAvailable Recharge Plans:");

        System.out.println("Plan ID   Amount   Validity   Benefits");

        for (RechargePlan plan : getRechargePlans()) {
            System.out.println(
                plan.getPlanId() + "        Rs." +
                plan.getAmount() + "     " +
                plan.getValidity() + "   " +
                plan.getBenefits()
            );
        }

        // Select recharge plan
        System.out.print("\nEnter Plan ID: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Plan Selection!");
            sc.close();
            return;
        }

        int choice = sc.nextInt();

        RechargePlan selectedPlan = getPlanById(choice);

        if (selectedPlan == null) {
            System.out.println("Invalid Plan Selection!");
            sc.close();
            return;
        }

        // Calculate recharge amount
        int amount = calculateRechargeAmount(selectedPlan);

        if (!isValidRechargeAmount(amount)) {
            System.out.println("Invalid Recharge Amount!");
            sc.close();
            return;
        }

        // Display transaction result
        System.out.println("\n===== RECHARGE DETAILS =====");
        System.out.println("Mobile Number: " + mobile);
        System.out.println("Selected Plan: " + selectedPlan.getPlanId());
        System.out.println("Recharge Amount: Rs." + amount);
        System.out.println("Validity: " + selectedPlan.getValidity());
        System.out.println("Benefits: " + selectedPlan.getBenefits());

        System.out.println("\nTransaction Status: SUCCESS");
        System.out.println("Recharge Completed Successfully!");

        sc.close();
    }
}
