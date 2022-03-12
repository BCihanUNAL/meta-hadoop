SUMMARY = "The Apache Hadoop software library is a framework that allows for the distributed processing of large data sets across clusters of computers using simple programming models."
HOMEPAGE = "https://hadoop.apache.org/"
SRC_URI = "https://dlcdn.apache.org/${PN}/common/${PN}-${PV}/${PN}-${PV}-${TARGET_ARCH}.tar.gz"
SRC_URI[md5sum] = "a99ced28839608d351f9cd8686e33012"

PREFERRED_PROVIDER_virtual/java2-runtime = "openjre-8"
PREFERRED_PROVIDER_virtual/ssh = "openssh"
PREFERRED_VERSION_bash = "4.4.20"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e2a717c4e826b924d6c68b397f4d44f1"

RDEPENDS_${PN} = "openssh openjre-8 bash ncurses"
RPROVIDES_${PN} = "hadoop"

do_configure_append() {
	#"export JAVA_HOME=${libdir}/jvm/openjre-8-${TARGET_ARCH}" >> ${WORKDIR}/${PN}-${PV}/etc/hadoop.env.sh
}

do_install() {
	install -d ${D}/home/root/${PN}-${PV} # not using a variable for now
	cp -r ${WORKDIR}/${PN}-${PV}/* ${D}/home/root/${PN}-${PV} # not using a variable for now
}

PACKAGES = "${PN}"
FILES_${PN} += "/home/root/${PN}-${PV}"

#need to skip do_package_qa, yocto wants the static libs in /lib folder that needs to be in hadoop/lib etc.
do_package_qa[noexec] = "1"
