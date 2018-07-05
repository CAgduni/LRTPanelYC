inherit image_types

# Set alignment to 4MB [in KiB]
IMAGE_ROOTFS_ALIGNMENT = "1024"

do_image_sdcard[depends] = "dosfstools-native:do_populate_sysroot \
                        virtual/kernel:do_deploy"


SDCARD = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.sdcard"

IMAGE_CMD_sdcard () {
	rm -f ${DEPLOY_DIR_IMAGE}/*.sdcard

	SDCARD_SIZE=$(expr ${IMAGE_ROOTFS_SIZE} / 1024 + 1) 
	dd if=/dev/zero of=${SDCARD} bs=1M count=${SDCARD_SIZE}
	dd if=${DEPLOY_DIR_IMAGE}/SPL conv=notrunc of=${SDCARD} seek=1 bs=1k
	dd if=${DEPLOY_DIR_IMAGE}/u-boot.img conv=notrunc of=${SDCARD} seek=69 bs=1k

	sfdisk ${SDCARD} <<-__EOF__
	1M,,L,*
	__EOF__

	dd if="${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.ext4" conv=notrunc of=${SDCARD} seek=1 bs=1M
}

IMAGE_TYPEDEP_sdcard = "ext4"



