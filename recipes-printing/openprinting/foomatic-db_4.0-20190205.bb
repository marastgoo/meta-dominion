SUMMARY = "The collected knowledge about printers, drivers, and driver options in XML files, used by foomatic-db-engine to generate PPD files."

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=89aead728f64ad2af95c03f5793274bc"

SRC_URI = "http://www.openprinting.org/download/foomatic/foomatic-db-${PV}.tar.gz"
SRC_URI[md5sum] = "6b95c359c3369c98307fcaf23841340d"
SRC_URI[sha256sum] = "e82b1f4f0216ed16f02da17ba7d162648a275d586788d9b6baaaf8844529613b"

S = "${WORKDIR}/${BPN}-20190205"

inherit autotools-brokensep pkgconfig allarch

FILES_${PN} += "${datadir}/foomatic/ \
                ${datadir}/cups \
               "

PACKAGES =+ "${PN}-de ${PN}-es ${PN}-fr ${PN}-it ${PN}-pt"

FILES_${PN}-de = "${datadir}/foomatic/db/source/PPD/*/de"
FILES_${PN}-es = "${datadir}/foomatic/db/source/PPD/*/es"
FILES_${PN}-fr = "${datadir}/foomatic/db/source/PPD/*/fr"
FILES_${PN}-it = "${datadir}/foomatic/db/source/PPD/*/it"
FILES_${PN}-pt = "${datadir}/foomatic/db/source/PPD/*/pt"
