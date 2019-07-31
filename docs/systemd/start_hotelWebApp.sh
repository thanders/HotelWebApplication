#!/bin/sh

# This bash script is part of the Systemd service for starting the web application.
# Should be placed in /usr/bin

# Path to webapp
Path_To_WebApp=/root/HotelWebApplication/

# If path exists, gradle tomcatrun else display message
if [[ -d "$Path_To_WebApp" ]]; then
    echo "Starting HotelWebApp service"
    ( cd $Path_To_WebApp ; gradle tomcatrun )

else
    echo "HotelWebApplication directory does not exist"
fi
