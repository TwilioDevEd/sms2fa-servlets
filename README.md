# SMS Two Factor Authentication with Servlets

SMS Two Factor Authentication implementation with Servlets and Twilio

[![Build Status](https://travis-ci.org/TwilioDevEd/sms2fa-servlets.svg?branch=master)](https://travis-ci.org/TwilioDevEd/sms2fa-servlets)

## Local Development

1. First clone this repository and `cd` into it.

   ```bash
   $ git clone git@github.com:TwilioDevEd/sms2fa-servlets.git
   $ cd sms2fa-servlets
   ```

1. Create the application.properties in src/main/resources and edit it to match your configuration;

   ```bash
   $ gradle generateProperties
   ```

1. Make sure the tests succeed.

   ```bash
   $ gradle check
   ```

1. Start the server.

   ```bash
   $ gradle appRun
   ```

1. Check it out at [http://localhost:8080](http://localhost:8080).

That's it!

## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.