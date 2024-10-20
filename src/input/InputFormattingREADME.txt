Order JSON Format

Introduction:
This document outlines the required format for JSON representation of orders and computers in a system.
Please adhere to the specified structure and guidelines to ensure successful processing of the input.
────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
    Guidelines:
    - "String" can be any series of characters enclosed within quotation marks.
    - Integer can be any whole number.
    - Double must be a finite number.
    - Boolean can only be true or false.

    Additional Notes:
    1. The "type" for the order must be labeled as either "pickup" or "shipping."
       - Specifics to note for an order of type "pickup":
         - Required: "pickupInfo" section with "billingAddress" and "pickupAddress."

       - Specifics to note for an order of type "shipping":
         - Required: "shippingInfo" section with "billingAddress," "shippingAddress," and "shippingCompany."

    2. The "type" for the computer must be labeled as either "gaming" or "laptop."
       - Specifics to note for a computer of type "gaming":
         - Required: "additionalHardware" section with specific gaming features.
         - Required: "computerCase", "cooler" and "powerSupply" section with their specific attributes

       - Specifics to note for a computer of type "laptop":
         - Required: "additionalHardware" section with specific laptop features.

    3. The "additionalStorage" can have its fields inputted, or it can be set to null, indicating no additional storage
       is desired for the given order.

    Format your input accordingly to ensure proper processing.
────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
Order JSON Structure:
{
  "orderStatus": {
    "currentStatus": "String",
    "statusNumber": Integer
  },
  "quantity": Integer,
  "type": "String",
  "customerInfo": {
    "firstName": "String",
    "lastName": "String",
    "email": "String",
    "phoneNumber": "String",
    "address": "String"
  },
  "pickupInfo": {
    "billingAddress": "String",
    "pickupAddress": "String"
  },
  "shippingInfo": {
    "billingAddress": "String",
    "shippingAddress": "String",
    "shippingCompany": "String"
  },
  "computer": {
    "type": "String",
    "primaryHardware": {
      "CPU": {
        "brand": "String",
        "model": "String",
        "price": Double,
        "cores": Integer,
        "threads": Integer,
        "processingSpeed": Double
      },
      "GPU": {
        "brand": "String",
        "model": "String",
        "price": Double,
        "rayTracing": Boolean,
        "clockSpeed": Double,
        "VRAM": Integer
      },
      "RAM": {
        "brand": "String",
        "model": "String",
        "price": Double,
        "capacity": Integer,
        "speedMHz": Integer,
        "type": "String"
      },
      "motherboard": {
        "brand": "String",
        "model": "String",
        "price": Double,
        "size": "String",
        "hasWifi": Boolean
      },
      "storage": {
        "brand": "String",
        "model": "String",
        "price": Double,
        "capacityGB": Integer,
        "speedMBps": Integer
      }
    },
    "additionalHardware": {
      "hasFingerprintScanner": Boolean,
      "hasTouchscreen": Boolean,
      "has360Hinge": Boolean,
      "hasRgbStrips": Boolean,
      "extraFansCount": Integer,
      "useNoiseCancellingFans": Boolean,
      "additionalStorage": {
        "brand": "String",
        "model": "String",
        "price": Double,
        "capacityGB": Integer,
        "speedMBps": Double
      }
    }
  }
}


