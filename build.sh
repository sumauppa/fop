#!/bin/sh
# This file should be executable.
echo
echo "Fop Build System"
echo "----------------"
echo

if [ "$JAVA_HOME" = "" ] ; then
  echo "ERROR: JAVA_HOME not found in your environment."
  echo
  echo "Please, set the JAVA_HOME variable in your environment to match the"
  echo "location of the Java Virtual Machine you want to use."
  exit 1
fi
LIBDIR=lib
LOCALCLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/classes.zip:$LIBDIR/ant.jar:$LIBDIR/ant-1.3-optional.jar:$LIBDIR/batik.jar:$LIBDIR/buildtools.jar:$LIBDIR/xerces-1.2.3.jar:$LIBDIR/xalan-2.0.0.jar:$LIBDIR/xalanj1compat.jar:$LIBDIR/bsf.jar
LOCALCLASSPATH=$LOCALCLASSPATH:$LIBDIR/jimi-1.0.jar:$LIBDIR/avalon-framework-cvs-20020315.jar

ANT_HOME=$LIBDIR

echo
echo Building with classpath $LOCALCLASSPATH
echo Starting Ant...
echo

$JAVA_HOME/bin/java -Dant.home=$ANT_HOME -classpath "$LOCALCLASSPATH" org.apache.tools.ant.Main $*
