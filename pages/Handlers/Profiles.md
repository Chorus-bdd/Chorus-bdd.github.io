---
layout: page
title: Profiles
section: Handlers
sectionIndex: 45
---

Sometimes you want to change handler configuration properties based on the environment in which your features are running.

e.g. When running locally, you want to stop and start a local process, but in UAT you want to connect to a process which is already running

To do this you can set configuration properties using a 'profile'

When you start chorus, pass in a profile name using the `-p profileName` switch

e.g.

    # By default tell the processes handler not to start a myProcess process,
    # and tell the remoting handler to connect remotely
    processes.myProcess.enabled=false
    remoting.myProcess.connection=jmx:myRemoteServer:1234

    # When running in localProfile, do start the process and connect to localhost instead
    profiles.localProfile.processes.myProcess.enabled=true
    profiles.localProfile.remoting.myProcess.connection=jmx:localhost:1234


When you run with `-p localProfile`, any configuration properties prefixed with `profiles.localProfile` will be used.
These may override similarly named properties which are declared without specifying the profile
