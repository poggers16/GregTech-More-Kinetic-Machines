package net.minecraft.server.packs;

import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.ResourceLocationException;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.slf4j.Logger;

public class FolderPackResources extends AbstractPackResources {
   private static final Logger LOGGER = LogUtils.getLogger();
   private static final boolean ON_WINDOWS = Util.getPlatform() == Util.OS.WINDOWS;
   private static final CharMatcher BACKSLASH_MATCHER = CharMatcher.is('\\');

   public FolderPackResources(File p_10257_) {
      super(p_10257_);
   }

   public static boolean validatePath(File p_10274_, String p_10275_) throws IOException {
      String s = p_10274_.getCanonicalPath();
      if (ON_WINDOWS) {
         s = BACKSLASH_MATCHER.replaceFrom(s, '/');
      }

      return s.endsWith(p_10275_);
   }

   protected InputStream getResource(String p_10277_) throws IOException {
      File file1 = this.getFile(p_10277_);
      if (file1 == null) {
         throw new ResourcePackFileNotFoundException(this.file, p_10277_);
      } else {
         return new FileInputStream(file1);
      }
   }

   protected boolean hasResource(String p_10279_) {
      return this.getFile(p_10279_) != null;
   }

   @Nullable
   private File getFile(String p_10282_) {
      try {
         File file1 = new File(this.file, p_10282_);
         if (file1.isFile() && validatePath(file1, p_10282_)) {
            return file1;
         }
      } catch (IOException ioexception) {
      }

      return null;
   }

   public Set<String> getNamespaces(PackType p_10259_) {
      Set<String> set = Sets.newHashSet();
      File file1 = new File(this.file, p_10259_.getDirectory());
      File[] afile = file1.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY);
      if (afile != null) {
         for(File file2 : afile) {
            String s = getRelativePath(file1, file2);
            if (s.equals(s.toLowerCase(Locale.ROOT))) {
               set.add(s.substring(0, s.length() - 1));
            } else {
               this.logWarning(s);
            }
         }
      }

      return set;
   }

   public void close() {
   }

   public Collection<ResourceLocation> getResources(PackType p_215329_, String p_215330_, String p_215331_, Predicate<ResourceLocation> p_215332_) {
      File file1 = new File(this.file, p_215329_.getDirectory());
      List<ResourceLocation> list = Lists.newArrayList();
      this.listResources(new File(new File(file1, p_215330_), p_215331_), p_215330_, list, p_215331_ + "/", p_215332_);
      return list;
   }

   private void listResources(File p_215334_, String p_215335_, List<ResourceLocation> p_215336_, String p_215337_, Predicate<ResourceLocation> p_215338_) {
      File[] afile = p_215334_.listFiles();
      if (afile != null) {
         for(File file1 : afile) {
            if (file1.isDirectory()) {
               this.listResources(file1, p_215335_, p_215336_, p_215337_ + file1.getName() + "/", p_215338_);
            } else if (!file1.getName().endsWith(".mcmeta")) {
               try {
                  String s = p_215337_ + file1.getName();
                  ResourceLocation resourcelocation = ResourceLocation.tryBuild(p_215335_, s);
                  if (resourcelocation == null) {
                     LOGGER.warn("Invalid path in datapack: {}:{}, ignoring", p_215335_, s);
                  } else if (p_215338_.test(resourcelocation)) {
                     p_215336_.add(resourcelocation);
                  }
               } catch (ResourceLocationException resourcelocationexception) {
                  LOGGER.error(resourcelocationexception.getMessage());
               }
            }
         }
      }

   }
}