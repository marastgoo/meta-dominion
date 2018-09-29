SUMMARY = "Out of tree build for the new cake qdisc"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://sch_cake.c;beginline=1;endline=36;md5=0af14571e97cad521fe4fb190d841633"

# Made up version
PV = "2018.08"

SRCREV = "9f052d92953501b8808928a3b0c89b27742e0de2"
SRC_URI = "git://github.com/dtaht/sch_cake.git;protocol=https;branch=master" 

S = "${WORKDIR}/git"

inherit module

EXTRA_OEMAKE += "KDIR=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/sched/
	install -m 0644 sch_cake.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/net/sched/
}

RDEPENDS_${PN} += "iproute2-tc"
