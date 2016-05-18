# SMS Two Factor Authentication with Servlets

SMS Two Factor Authentication implementation with Servlets and Twilio.

[![Build Status](https://travis-ci.org/TwilioDevEd/sms2fa-servlets.svg?branch=master)](https://travis-ci.org/TwilioDevEd/sms2fa-servlets)

## Requirements

[Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)

## Local Development

1. First clone this repository and `cd` into it.

   ```bash
   $ git clone git@github.com:TwilioDevEd/sms2fa-servlets.git
   $ cd sms2fa-servlets
   ```

1. Edit the sample configuration file `.env.example` and edit it to match your configuration.

   Once you have edited the `.env.example` file, if you are using a unix operating system,
   just use the `source` command to load the variables into your environment:

   ```bash
   $ source .env.example
   ```

   If you are using a different operating system, make sure that all the
   variables from the .env.example file are loaded into your environment.

1. Make sure the tests succeed.

   ```bash
   $ ./gradlew check
   ```

1. Start the server.

   ```bash
   $ ./gradlew appRun
   ```

1. Check it out at [http://localhost:8080](http://localhost:8080).

That's it!

## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.
